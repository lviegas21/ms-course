package com.example.hr_worker.service;

import com.example.hr_worker.domain.entities.Worker;
import com.example.hr_worker.dto.UserAcessDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class OnlineUserLogService {
    // ✅ Definindo um logger exclusivo para este serviço
    private static final Logger onlineUserLogger = LoggerFactory.getLogger("OnlineUserLogger");

    private static final String LOG_FILE = "logs/online-users.log";
    private final ObjectMapper objectMapper = new ObjectMapper();

    public void updateUserActivity(Worker worker) {
        LocalDateTime now = LocalDateTime.now();
        try {
            // Convertendo Worker para JSON válido
            String workerJson = objectMapper.writeValueAsString(worker);

            // Criando a entrada de log corretamente formatada
            String newLogEntry = workerJson + " | lastAccess=" + now;
            Path logPath = Paths.get(LOG_FILE);
            // Criar o arquivo de log se não existir
            if (Files.notExists(logPath)) {
                Files.createDirectories(logPath.getParent()); // Garante que a pasta "logs" exista
                Files.createFile(logPath);
            }
            List<String> lines = Files.readAllLines(logPath);
            List<String> updatedLogs = lines.stream()
                    .filter(line -> !line.contains("\"id\":" + worker.getId())) // Remove entradas antigas desse usuário
                    .collect(Collectors.toList());
            updatedLogs.add(newLogEntry);
            Files.write(logPath, updatedLogs);
        } catch (IOException e) {
            onlineUserLogger.error("Erro ao atualizar o log do usuário", e);
        }
    }

    public List<UserAcessDTO> buscarUsersOnline() {
        List<UserAcessDTO> usersOnline = new ArrayList<>();
        Path logPath = Paths.get(LOG_FILE);

        if (Files.notExists(logPath)) {
            onlineUserLogger.warn("Arquivo de log não encontrado. Nenhum usuário online.");
            return usersOnline;
        }

        try {
            List<String> lines = Files.readAllLines(logPath);

            for (String line : lines) {
                // Filtra apenas linhas que começam com '{' (indicando um JSON válido)
                if (!line.trim().startsWith("{")) {
                    continue; // Ignora logs do Spring Boot
                }

                // Separando JSON do lastAccess
                String[] parts = line.split("\\| lastAccess=");

                if (parts.length == 2) {
                    String jsonPart = parts[0].trim();
                    String lastAccessStr = parts[1].trim();

                    // Convertendo JSON para UserAcessDTO
                    JsonNode jsonNode = objectMapper.readTree(jsonPart);
                    UserAcessDTO user = new UserAcessDTO();
                    user.setId(jsonNode.get("id").asInt());
                    user.setName(jsonNode.get("name").asText());
                    user.setDailyIncome(jsonNode.get("dailyIncome").asDouble());
                    user.setLastAccess(LocalDateTime.parse(lastAccessStr));

                    usersOnline.add(user);
                }
            }
        } catch (IOException e) {
            onlineUserLogger.error("Erro ao ler logs dos usuários online", e);
        }

        return usersOnline;
    }
}
