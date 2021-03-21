# Desafio Backend Java


## O Desafio
### O desafio se trata de atualização das assinatura do usuário de acordo com notificações.
<img src="DesafioFila.png" width="500" height="500">


#### Tipos de Notificações:

-  SUBSCRIPTION_PURCHASED - A Compra foi realizada e a assinatura deve estar com status ativa.

-  SUBSCRIPTION_CANCELED - A Compra foi cancelada e a assinatura deve estar com status cancelada.

-  SUBSCRIPTION_RESTARTED - A Compra foi recuperada e a assinatura deve estar com status ativa.


### Etapas

- Recebimento Notificação HTTP
- Enfileiramento
- Processamento e Persistencia

## Considerações Gerais

Esse problema tem algumas constraints:

1. Linguagem : Java 8 ou mais atualizada

2. Framework: Spring

3. Database: Qualquer database relacional

4. Deve ser possível conseguir rodar seu código em um Mac OS X OU no Ubuntu;

5. O RabbitMQ deve ficar dentro de um compose(Docker)

6. Devemos ser capazes de executar o seu código em um ambiente local de alguma forma automatizada:

   ``` git clone seu-repositorio
    cd seu-repositorio
    ./configure (ou algo similar)
    docker-compose up -d
    make run (ou algo similar como java -jar desafio.jar)
    make sendNotifications 
    
    
    obs: Não necessariamente deve ser dessa forma, mas precisa estar automatizada e documentado.
   ```

7. Devemos ter automatizadas as chamadas para a api com as notificações em anexo no arquivo [notificacoes.txt], obrigatoriamente seguindo a ordem do arquivo (OBS: Pode ser feito em qualquer linguagem de programação). 

Esses comandos devem ser o suficiente para inicializar o RabbitMQ, a aplicação Java e as chamadas para a API. Pode se considerar que temos instalado no meu sistema: Java, Python e Ruby e Docker.
Qualquer outra dependência que eu precisar você tem que prover.

## Modelo do Banco de Dados

![Modelo](database_model.png)


## Execução
Após as inicialização do ambiente(Docker/Rabbit/Aplicação), executar o script de envio das Notificações e espera-se que o status da assinatura esteja conforme as notificações recebidas, além disso deve ter conter todo o histórico de notificações para cada assinatura para um possível auditoria.
