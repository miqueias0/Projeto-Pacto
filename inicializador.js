const { exec } = require('child_process');
const { spawn } = require('child_process');

const bildarComum = [
    'mvn -f comum clean install',
    // Adicione mais microservices conforme necessário
];

const microservices = [
    // 'java -jar gateway/target/gateway-0.0.1-SNAPSHOT.jar',
    'java -jar notificacao/target/notificacao-0.0.1-SNAPSHOT.jar',
    'java -jar candidatura/target/candidatura-0.0.1-SNAPSHOT.jar',
    'java -jar seguranca/target/seguranca-0.0.1-SNAPSHOT.jar',
    'java -jar usuario/target/usuario-0.0.1-SNAPSHOT.jar',
    'java -jar vaga/target/vaga-0.0.1-SNAPSHOT.jar',
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
                inicializadorAsincrono();
            } else {
                console.error(`Erro ao iniciar microserviço ${index + 1}`);
            }
        });
    }
}

// Inicia os microservices de forma síncrona, começando pelo primeiro
inicializador(0);