const { spawn } = require('child_process');

const bildarComum = [
    'mvn -f comum clean install',
    'mvn -f notificacao clean install',
    'mvn -f candidatura clean install',
    'mvn -f seguranca clean install',
    'mvn -f vaga clean install',
    'mvn -f usuario clean install',
];

const microservices = [
    'java -jar notificacao/target/notificacao-0.0.1-SNAPSHOT.war',
    'java -jar candidatura/target/candidatura-0.0.1-SNAPSHOT.war',
    'java -jar seguranca/target/seguranca-0.0.1-SNAPSHOT.war',
    'java -jar usuario/target/usuario.war',
    'java -jar vaga/target/vaga-0.0.1-SNAPSHOT.war',
]

function inicializadorAsincrono() {
    microservices.forEach((command) => {
        const childProcess = spawn(command, { shell: true, stdio: 'inherit', detached: true });

        childProcess.unref();
    });
}

function inicializador(index) {
    if (index < bildarComum.length) {
        const command = bildarComum[index];
        const childProcess = spawn(command, { shell: true, stdio: 'inherit' });

        childProcess.on('exit', (code) => {
            if (code === 0) {
                // O microserviço foi iniciado com sucesso, inicia o próximo
                if (bildarComum.length > ++index) {
                    inicializador(index);
                } else {
                    inicializadorAsincrono();
                }
            } else {
                console.error(`Erro ao iniciar microserviço ${index + 1}`);
            }
        });
    }
}

// Inicia os microservices de forma síncrona, começando pelo primeiro
inicializador(0);