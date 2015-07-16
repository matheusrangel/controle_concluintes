package bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import model.Concluinte;
import model.Processo;
import dao.ConcluinteDAO;
import dao.ProcessoDAO;

@ManagedBean(name="painelBean")
public class PainelBean {
	
	private List<Concluinte> concluintesNovos;
	private List<Concluinte> concluintesAbertos;
	private List<Concluinte> concluintesFinalizados;
	
	private List<Processo> prcsNaCoord, procsEnviadosOrient, procsRecebidosOrient, prcsEnviadosCoordEst, prcsRecebidosCoordEst, prcsEnviadosCCA;
	
	
	@PostConstruct
	public void init() {
		ConcluinteDAO concluinteDAO = new ConcluinteDAO();
		ProcessoDAO processoDAO = new ProcessoDAO();
		this.concluintesNovos = new ArrayList<Concluinte>();
		this.concluintesAbertos = new ArrayList<Concluinte>();
		this.concluintesFinalizados = new ArrayList<Concluinte>();
		
		List<Concluinte> concluintes = concluinteDAO.findAll();
		if (concluintes != null && !concluintes.isEmpty()) {
			for (Concluinte concluinte : concluintes) {
				switch (concluinte.getSituacao()) {
				case Novo:
					this.concluintesNovos.add(concluinte);
					break;
				case Aberto:
					this.concluintesAbertos.add(concluinte);
					break;
				case Finalizado:
					this.concluintesFinalizados.add(concluinte);
					break;

				default:
					break;
				}
			}
		}
		
		this.prcsNaCoord = new ArrayList<Processo>();
		this.procsEnviadosOrient = new ArrayList<Processo>();
		this.procsRecebidosOrient = new ArrayList<Processo>();
		this.prcsEnviadosCoordEst = new ArrayList<Processo>();
		this.prcsRecebidosCoordEst = new ArrayList<Processo>();
		this.prcsEnviadosCCA = new ArrayList<Processo>();
		
		List<Processo> processos = processoDAO.findAll();
		if (processos != null && !processos.isEmpty()) {
			for (Processo processo : processos) {
				switch (processo.getStatus()) {
				case ChegouNaCoordenacao:
					this.prcsNaCoord.add(processo);
					break;
				case EnviadoParaOrientador:
					this.procsEnviadosOrient.add(processo);
					break;
				case RecebidoDoOrientador:
					this.procsRecebidosOrient.add(processo);
					break;
				case EnviadoParaCoordEstagio:
					this.prcsEnviadosCoordEst.add(processo);
					break;
				case RecebidoDaCoordEstagio:
					this.prcsRecebidosCoordEst.add(processo);
				case EnviadoParaCCA:
					this.prcsEnviadosCCA.add(processo);
					break;
				
				default:
					break;
				}
			}
		}
		
	}
	
	
	public List<Concluinte> getConcluintesNovos() {
		return concluintesNovos;
	}
	public void setConcluintesNovos(List<Concluinte> concluintesNovos) {
		this.concluintesNovos = concluintesNovos;
	}
	public List<Concluinte> getConcluintesAbertos() {
		return concluintesAbertos;
	}
	public void setConcluintesAbertos(List<Concluinte> concluintesAbertos) {
		this.concluintesAbertos = concluintesAbertos;
	}
	public List<Concluinte> getConcluintesFinalizados() {
		return concluintesFinalizados;
	}
	public void setConcluintesFinalizados(List<Concluinte> concluintesFinalizados) {
		this.concluintesFinalizados = concluintesFinalizados;
	}


	public List<Processo> getPrcsNaCoord() {
		return prcsNaCoord;
	}
	public void setPrcsNaCoord(List<Processo> prcsNaCoord) {
		this.prcsNaCoord = prcsNaCoord;
	}
	public List<Processo> getProcsEnviadosOrient() {
		return procsEnviadosOrient;
	}
	public void setProcsEnviadosOrient(List<Processo> procsEnviadosOrient) {
		this.procsEnviadosOrient = procsEnviadosOrient;
	}
	public List<Processo> getProcsRecebidosOrient() {
		return procsRecebidosOrient;
	}
	public void setProcsRecebidosOrient(List<Processo> procsRecebidosOrient) {
		this.procsRecebidosOrient = procsRecebidosOrient;
	}
	public List<Processo> getPrcsEnviadosCoordEst() {
		return prcsEnviadosCoordEst;
	}
	public void setPrcsEnviadosCoordEst(List<Processo> prcsEnviadosCoordEst) {
		this.prcsEnviadosCoordEst = prcsEnviadosCoordEst;
	}
	public List<Processo> getPrcsRecebidosCoordEst() {
		return prcsRecebidosCoordEst;
	}
	public void setPrcsRecebidosCoordEst(List<Processo> prcsRecebidosCoordEst) {
		this.prcsRecebidosCoordEst = prcsRecebidosCoordEst;
	}
	public List<Processo> getPrcsEnviadosCCA() {
		return prcsEnviadosCCA;
	}
	public void setPrcsEnviadosCCA(List<Processo> prcsEnviadosCCA) {
		this.prcsEnviadosCCA = prcsEnviadosCCA;
	}
	
	
}
