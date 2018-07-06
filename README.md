# Meus pedidos Test

Este projeto tem como objetivo executar o teste do "Meus Pedidos".

## Como executo isto?

Para compilar o projeto, deve-se ter instalado Docker, java e maven na máquina.

Compilar: `mvn clean package docker:build`
Executar: `docker run --rm -ti ramonnteixeira/meuspedidos-test {caminho_arquivo}`

O container docker já se encontra no docker hub, em modo público, logo, pode-se executar diretamente.

