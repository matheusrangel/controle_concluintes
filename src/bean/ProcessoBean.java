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
	private Date dataEnvioOrientador;
	private Date dataRecebidoOrientador;
	private Date dataEnviadoCoordEst;
	private Date dataRecebidoCoordEstagio;
	private Date dataEnviadoCCA;
	
	
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
		
		if (getProcesso().getStatus() != null) {
			switch (getProcesso().getStatus()) {
			case ChegouNaCoordenacao:
				this.setNumero(getProcesso().getNumero());
				this.setDataAbertura(this.processo.getEventos().get(StatusProcesso.Aberto.getValue()).getData());
				this.setDataChegadaCoord(this.processo.getEventos().get(StatusProcesso.ChegouNaCoordenacao.getValue()).getData());
				break;
			case EnviadoParaOrientador:
				this.setNumero(getProcesso().getNumero());
				this.setDataAbertura(this.processo.getEventos().get(StatusProcesso.Aberto.getValue()).getData());
				this.setDataChegadaCoord(this.processo.getEventos().get(StatusProcesso.ChegouNaCoordenacao.getValue()).getData());
				this.setDataEnvioOrientador(this.processo.getEventos().get(StatusProcesso.EnviadoParaOrientador.getValue()).getData());
				break;
			case RecebidoDoOrientador:
				this.setNumero(getProcesso().getNumero());
				this.setDataAbertura(this.processo.getEventos().get(StatusProcesso.Aberto.getValue()).getData());
				this.setDataChegadaCoord(this.processo.getEventos().get(StatusProcesso.ChegouNaCoordenacao.getValue()).getData());
				this.setDataEnvioOrientador(this.processo.getEventos().get(StatusProcesso.EnviadoParaOrientador.getValue()).getData());
				this.setDataRecebidoOrientador(this.processo.getEventos().get(StatusProcesso.RecebidoDoOrientador.getValue()).getData());
				break;
			case EnviadoParaCoordEstagio:
				this.setNumero(getProcesso().getNumero());
				this.setDataAbertura(this.processo.getEventos().get(StatusProcesso.Aberto.getValue()).getData());
				this.setDataChegadaCoord(this.processo.getEventos().get(StatusProcesso.ChegouNaCoordenacao.getValue()).getData());
				this.setDataEnvioOrientador(this.processo.getEventos().get(StatusProcesso.EnviadoParaOrientador.getValue()).getData());
				this.setDataRecebidoOrientador(this.processo.getEventos().get(StatusProcesso.RecebidoDoOrientador.getValue()).getData());
				this.setDataEnviadoCoordEst(this.processo.getEventos().get(StatusProcesso.EnviadoParaCoordEstagio.getValue()).getData());
				break;
			case RecebidoDaCoordEstagio:
				this.setNumero(getProcesso().getNumero());
				this.setDataAbertura(this.processo.getEventos().get(StatusProcesso.Aberto.getValue()).getData());
				this.setDataChegadaCoord(this.processo.getEventos().get(StatusProcesso.ChegouNaCoordenacao.getValue()).getData());
				this.setDataEnvioOrientador(this.processo.getEventos().get(StatusProcesso.EnviadoParaOrientador.getValue()).getData());
				this.setDataRecebidoOrientador(this.processo.getEventos().get(StatusProcesso.RecebidoDoOrientador.getValue()).getData());
				this.setDataEnviadoCoordEst(this.processo.getEventos().get(StatusProcesso.EnviadoParaCoordEstagio.getValue()).getData());
				this.setDataRecebidoCoordEstagio(this.processo.getEventos().get(StatusProcesso.RecebidoDaCoordEstagio.getValue()).getData());
				break;
			case EnviadoParaCCA:
				this.setNumero(getProcesso().getNumero());
				this.setDataAbertura(this.processo.getEventos().get(StatusProcesso.Aberto.getValue()).getData());
				this.setDataChegadaCoord(this.processo.getEventos().get(StatusProcesso.ChegouNaCoordenacao.getValue()).getData());
				this.setDataEnvioOrientador(this.processo.getEventos().get(StatusProcesso.EnviadoParaOrientador.getValue()).getData());
				this.setDataRecebidoOrientador(this.processo.getEventos().get(StatusProcesso.RecebidoDoOrientador.getValue()).getData());
				this.setDataEnviadoCoordEst(this.processo.getEventos().get(StatusProcesso.EnviadoParaCoordEstagio.getValue()).getData());
				this.setDataRecebidoCoordEstagio(this.processo.getEventos().get(StatusProcesso.RecebidoDaCoordEstagio.getValue()).getData());
				this.setDataEnviadoCCA(this.processo.getEventos().get(StatusProcesso.EnviadoParaCCA.getValue()).getData());
				break;

			default:
				break;
			}
		}
		
	}
	
	public void criarFlash() {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.put("processo", this.processo);
		flash.put("concluinte", this.concluinte);
	}
	
	
	public String abrirProcesso(Long idConcluinte) {
		ConcluinteDAO concluinteDAO = new ConcluinteDAO();
		this.concluinte = concluinteDAO.findById(idConcluinte);
		
		if (!this.processos.isEmpty()) {
			for (Processo processoAux : this.processos) {
				if (processoAux.getConcluinte() != null && processoAux.getConcluinte().equals(this.concluinte)) {
					this.processo = processoAux;
					this.processo.setConcluinte(this.concluinte);
				}
			}
		}
		if (this.processo == null) {
			this.processo = new Processo();
			this.processo.setConcluinte(concluinte);
		}
		criarFlash();
		
		return "cadastro_processo";
	}
	
	
	public String cadastrar() {
		ProcessoDAO processoDAO = new ProcessoDAO();
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
	
//	public String prosseguir(Long idProcesso) {
//		ProcessoDAO processoDAO = new ProcessoDAO();
//		this.processo = processoDAO.findById(idProcesso);
//		this.concluinte = this.processo.getConcluinte();
//		criarFlash();
//		
//		return "cadastro_processo";
//	}
	
	public String incrementar() {
		ProcessoDAO processoDAO = new ProcessoDAO();
		switch (getProcesso().getStatus()) {
		case ChegouNaCoordenacao:
			Evento enviadoOrientador = new Evento(this.processo, StatusProcesso.EnviadoParaOrientador.getValue(), this.dataEnvioOrientador);
			this.processo.getEventos().add(enviadoOrientador);
			break;
		case EnviadoParaOrientador:
			Evento recedidoOrientador = new Evento(this.processo, StatusProcesso.RecebidoDoOrientador.getValue(), this.dataRecebidoOrientador);
			this.processo.getEventos().add(recedidoOrientador);
			break;
		case RecebidoDoOrientador:
			Evento enviadoCoordEst = new Evento(this.processo, StatusProcesso.EnviadoParaCoordEstagio.getValue(), this.dataEnviadoCoordEst);
			this.processo.getEventos().add(enviadoCoordEst);
			break;
		case EnviadoParaCoordEstagio:
			Evento recebidoCoordEst = new Evento(this.processo, StatusProcesso.RecebidoDaCoordEstagio.getValue(), this.dataRecebidoCoordEstagio);
			this.processo.getEventos().add(recebidoCoordEst);
			break;
		case RecebidoDaCoordEstagio:
			Evento enviadoCCA = new Evento(this.processo, StatusProcesso.EnviadoParaCCA.getValue(), this.dataEnviadoCCA);
			this.processo.getEventos().add(enviadoCCA);
			break;
		default:
			break;
		}
		
		processoDAO.update(this.processo);
		
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
	public Date getDataEnvioOrientador() {
		return dataEnvioOrientador;
	}
	public void setDataEnvioOrientador(Date dataEnvioOrientador) {
		this.dataEnvioOrientador = dataEnvioOrientador;
	}
	public Date getDataRecebidoOrientador() {
		return dataRecebidoOrientador;
	}
	public void setDataRecebidoOrientador(Date dataRecebidoOrientador) {
		this.dataRecebidoOrientador = dataRecebidoOrientador;
	}
	public Date getDataEnviadoCoordEst() {
		return dataEnviadoCoordEst;
	}
	public void setDataEnviadoCoordEst(Date dataEnviadoCoordEst) {
		this.dataEnviadoCoordEst = dataEnviadoCoordEst;
	}
	public Date getDataRecebidoCoordEstagio() {
		return dataRecebidoCoordEstagio;
	}
	public void setDataRecebidoCoordEstagio(Date dataRecebidoCoordEstagio) {
		this.dataRecebidoCoordEstagio = dataRecebidoCoordEstagio;
	}
	public Date getDataEnviadoCCA() {
		return dataEnviadoCCA;
	}
	public void setDataEnviadoCCA(Date dataEnviadoCCA) {
		this.dataEnviadoCCA = dataEnviadoCCA;
	}
	
}
