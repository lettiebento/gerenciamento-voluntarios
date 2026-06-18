@echo off
chcp 65001 > nul
echo ===============================================
echo  Extensao em Acao - Gerenciamento de Voluntarios
echo ===============================================
where java >nul 2>nul || (echo ERRO: Java nao encontrado. Instale o JDK 17 ou superior.& pause & exit /b 1)
where mvn >nul 2>nul || (echo ERRO: Maven nao encontrado. Instale o Maven e adicione-o ao PATH.& pause & exit /b 1)
echo Iniciando o sistema em http://localhost:8080
mvn spring-boot:run
pause
