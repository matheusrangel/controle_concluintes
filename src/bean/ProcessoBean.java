package bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import model.Concluinte;
import model.Evento;
import model.Processo;
import auxiliar.SituacaoConcluinte;
import auxiliar.StatusProcesso;
import dao.ConcluinteDAO;
import dao.EventoDAO;
import dao.ProcessoDAO;

@ManagedBean(name="processoBean")
@ViewScoped
public class ProcessoBean {
	private List<Processo> processos;
	private DataModel<Processo> model;
	private Processo processo;
	
	private String numero;
	private Concluinte concluinte;
	private List<Evento> eventos;
	private Date dataAbertura;
	private Date dataChegadaCoord;
	
	
	@PostConstruct
	public void carregarDados() {
		ProcessoDAO processoDAO = new ProcessoDAO();
		this.processos = processoDAO.findAll();
		
		this.model = new ListDataModel<Processo>(this.processos);
	}
	
	public void carregarFlash() {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		this.setConcluinte((Concluinte) flash.get("concluinte"));
		this.setProcesso((Processo) flash.get("processo"));
	}
	
	public String abrirProcesso(Long idConcluinte) {
		ConcluinteDAO concluinteDAO = new ConcluinteDAO();
		this.concluinte = concluinteDAO.findById(idConcluinte);
		this.processo = new Processo();
		this.processo.setConcluinte(concluinte);
		
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.put("processo", this.processo);
		flash.put("concluinte", this.concluinte);
		
		return "cadastro_processo?faces-redirect=true";
	}
	
	
	public String cadastrar() {
		ProcessoDAO processoDAO = new ProcessoDAO();
		ConcluinteDAO concluinteDAO = new ConcluinteDAO();
		Evento aberto = new Evento(this.processo, StatusProcesso.Aberto.getValue(), this.dataAbertura);
		Evento naCoordenacao = new Evento(this.processo, StatusProcesso.ChegouNaCoordenacao.getValue(), this.dataChegadaCoord);
		List<Evento> eventos = new ArrayList<Evento>();
		eventos.add(aberto);
		eventos.add(naCoordenacao);
		this.processo.setEventos(eventos);
		this.processo.setNumero(this.numero);
		this.concluinte.setSituacao(SituacaoConcluinte.Aberto);
		this.processo.setConcluinte(concluinte);
		processoDAO.persist(this.processo);
		
		return "processos?faces-redirect=true";
	}
	
	
	public List<Processo> getProcessos() {
		return processos;
	}
	public void setProcessos(List<Processo> processos) {
		this.processos = processos;
	}
	public DataModel<Processo> getModel() {
		return model;
	}
	public void setModel(DataModel<Processo> model) {
		this.model = model;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public Concluinte getConcluinte() {
		return concluinte;
	}
	public void setConcluinte(Concluinte concluinte) {
		this.concluinte = concluinte;
	}
	public List<Evento> getEventos() {
		return eventos;
	}
	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}
	public Date getDataAbertura() {
		return dataAbertura;
	}
	public void setDataAbertura(Date dataAbertura) {
		this.dataAbertura = dataAbertura;
	}
	public Date getDataChegadaCoord() {
		return dataChegadaCoord;
	}
	public void setDataChegadaCoord(Date dataChegadaCoord) {
		this.dataChegadaCoord = dataChegadaCoord;
	}

	public Processo getProcesso() {
		return processo;
	}

	public void setProcesso(Processo processo) {
		this.processo = processo;
	}
	
}
