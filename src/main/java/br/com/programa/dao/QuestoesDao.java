package br.com.programa.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.programa.model.Questao;
import br.com.programa.model.Tema;

public class QuestoesDao implements DaoGenerico{
	private List<Questao> questoes;
	private Long id;
	
	public QuestoesDao() {
		questoes = new ArrayList<Questao>();
		id = 0L;
	}
	
	@Override
	public boolean salvar(Object questao) {
		boolean saida;
		saida = false;
		Questao quest = (Questao) questao; 
		quest.setId(id + 1);
		Tema tema = (Tema) Gerente.getTemaDao().buscar(quest.getTema().getId());
		tema.getQuestoes().add(quest);
		Gerente.getTemaDao().Alterar(tema);
		if ( !questoes.contains(quest)) {			
			questoes.add(quest);
			saida = true;
		}
		return saida;
	}
	
	public boolean remover(Questao questao) {
		boolean saida;
		saida = false;
		if ( questoes.contains(questao)) {
			questoes.remove(questao);
			saida = true;
		}
		return saida;
	}
	
	public boolean remover(Long id) {
		boolean saida;
		Questao questao = new Questao();
		questao.setId(id);
		saida = false;
		if (questoes.contains(questao)) {
			questoes.remove(questao);
			saida = true;
		}
		return saida;
	}
	
	public Questao buscar(Long id) {
		for (Questao questao : questoes) {
			if (questao.getId() == id)
				return questao;
		}
		return null;
	}
	public List<Questao> BuscarTodos(){
		return questoes;
	}

}