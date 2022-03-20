package telegram_bot;

import java.util.HashMap;
import java.util.List;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ChatAction;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.request.SendChatAction;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.BaseResponse;
import com.pengrad.telegrambot.response.GetUpdatesResponse;
import com.pengrad.telegrambot.response.SendResponse;

public class Main {
	    
	public static void main(String[] args) {
		
		final String MEU_BOT = "5129788142:AAHAyyEguv51zDQUeph6k4s_ABAZBiRVUUc"; 
		
		// Criacao do objeto bot com as informacoes de acesso.
		TelegramBot bot = new TelegramBot(MEU_BOT);
        		
		// Objeto responsavel por receber as mensagens.
		GetUpdatesResponse updatesResponse;

		// Objeto responsavel por gerenciar o envio de respostas.
		SendResponse sendResponse;

		// Objeto responsavel por gerenciar o envio de acoes do chat.
		BaseResponse baseResponse;

		// Controle de off-set, isto e, a partir deste ID sera lido as mensagens
		// pendentes na fila.
		int m = 0;

		// Loop infinito pode ser alterado por algum timer de intervalo curto.
		while (true) {
			// Executa comando no Telegram para obter as mensagens pendentes a partir de um
			// off-set (limite inicial).
			updatesResponse = bot.execute(new GetUpdates().limit(100).offset(m));

			// Lista de mensagens.
			List<Update> updates = updatesResponse.updates();

			// Analise de cada acao da mensagem.
			for (Update update : updates) {

				// Atualizacao do off-set.
				m = update.updateId() + 1;

				System.out.println("UPDATE_ID :"  + update.updateId());
				System.out.println("CHAT_ID   :"  + update.message().chat().id());
				System.out.println("MESSAGE_ID:"  + update.message().messageId());
				
				System.out.println("Recebendo mensagem: " + update.message().text());
							
				Cliente cliente = consultaCliente(update);
				
				// Envio de "Escrevendo" antes de enviar a resposta.
				baseResponse = bot.execute(new SendChatAction(cliente.getIdentificador(), ChatAction.typing.name()));

				// Verificacao de acao de chat foi enviada com sucesso.
				System.out.println("Resposta de Chat Action Enviada? " + baseResponse.isOk());

				// Envio da mensagem de resposta.
				sendResponse = bot.execute(new SendMessage(cliente.getIdentificador(), cliente.resposta()));

				// Verificacao de mensagem enviada com sucesso.
				System.out.println("Mensagem Enviada? " + sendResponse.isOk());
				
			}
		}
	}

	private static Cliente consultaCliente(Update update) {
		HashMap<Long, Cliente> clientes = new HashMap<Long, Cliente>();

		Long idCliente = update.message().chat().id();
		String nome = update.message().chat().firstName();
		String sobrenome = update.message().chat().lastName();
		Cliente cliente = new Cliente(idCliente, nome, sobrenome);
		if (!clientes.containsKey(idCliente)) {
		    Iteracao conversa = new Iteracao(cliente);
		    cliente.setConversa(conversa);
		    clientes.put(idCliente, cliente);
		} else {
			cliente = clientes.get(idCliente);
		}
		
		cliente.setPergunta(update.message().text());
		return cliente;
	}
}
