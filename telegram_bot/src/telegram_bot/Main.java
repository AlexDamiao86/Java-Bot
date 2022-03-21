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

				System.out.println(":: DISPLAY APENAS PARA TESTES ::");
				System.out.println("USER_ID   :" + update.message().from().id());
				System.out.println("CHAT_ID   :" + update.message().chat().id());
				System.out.println("UPDATE_ID :" + update.updateId());
				System.out.println("MESSAGE_ID:" + update.message().messageId());

				System.out.println("Recebendo mensagem: " + update.message().text());

				Cliente cliente = consultarCliente(update);

				Long idConversa = update.message().chat().id();
				Conversa conversa = cliente.iniciarConversa(idConversa);
				String estimulo = "";
				if (update.message().text() != null) {
					estimulo = update.message().text();
				}
				Iteracao interacao = new Iteracao(conversa, estimulo);

				// Consulta qual mensagem deve se enviar como resposta dependendo da interacao
				RespostaBot respostaBot = interacao.devolverResposta();

				System.out.println(":: DISPLAY APENAS PARA TESTES ::");
				System.out.println("estadoConversa: " + conversa.getIteracaoAtual().name());
				System.out.println("lengthResposta: " + respostaBot.getTexto().size());
				System.out.println("possuiTeclado : " + respostaBot.isPossuiTeclado());

				if (!respostaBot.isPossuiTeclado()) {
					// Frases da resposta sem teclado (resposta sem o teclado)
					for (int i = 0; i < respostaBot.getTexto().size(); i++) {
						baseResponse = bot
								.execute(new SendChatAction(conversa.getIdentificador(), ChatAction.typing.name()));
						sendResponse = bot
								.execute(new SendMessage(conversa.getIdentificador(), respostaBot.getTexto().get(i)));
						System.out.println("Resposta de Chat Action Enviada? " + baseResponse.isOk());
						System.out.println("Mensagem Enviada? " + sendResponse.isOk());
					}
				} else {
					// Para respostas com teclado (teclado aparece apenas na ultima frase enviada)
					// Frases da resposta sem o teclado
					for (int i = 0; i < (respostaBot.getTexto().size() - 1); i++) {
						baseResponse = bot
								.execute(new SendChatAction(conversa.getIdentificador(), ChatAction.typing.name()));
						sendResponse = bot
								.execute(new SendMessage(conversa.getIdentificador(), respostaBot.getTexto().get(i)));
						System.out.println("Resposta de Chat Action Enviada? " + baseResponse.isOk());
						System.out.println("Mensagem Enviada? " + sendResponse.isOk());
					}
					// Mostra teclado apenas na ultima frase do texto
					baseResponse = bot
							.execute(new SendChatAction(conversa.getIdentificador(), ChatAction.typing.name()));
					sendResponse = bot.execute(new SendMessage(conversa.getIdentificador(),
							respostaBot.getTexto().get(respostaBot.getTexto().size() - 1))
									.replyMarkup(respostaBot.getTeclado()));
					System.out.println("Resposta de Chat Action Enviada? " + baseResponse.isOk());
					System.out.println("Mensagem Enviada? " + sendResponse.isOk());
				}

			}
		}
	}

	private static Cliente consultarCliente(Update update) {
		HashMap<Long, Cliente> clientes = new HashMap<Long, Cliente>();

		Long idCliente = update.message().from().id();
		String nome = update.message().from().firstName();
		String sobrenome = update.message().from().lastName();

		Cliente cliente = null;

		if (!clientes.containsKey(idCliente)) {
			cliente = new Cliente(idCliente, nome, sobrenome);
			clientes.put(idCliente, cliente);
		} else {
			cliente = clientes.get(idCliente);
		}

		return cliente;
	}
}
