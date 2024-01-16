package br.com.mike.comum.records;

import java.util.List;

public record VagaRecord(String id, String titulo, String descricao, List<String> requisitos, String contratanteId) {
}
