package br.com.clinicaanimal.infra.view.showcase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;

import br.com.clinicaanimal.component.dialog.DialogBean;
import br.com.clinicaanimal.component.dialog.DialogType;
import br.com.clinicaanimal.infra.util.FacesUtil;
import br.com.clinicaanimal.infra.view.showcase.vo.Consulta;
import lombok.Getter;
import lombok.Setter;

@Named
@ViewScoped
@Getter
@Setter
public class ConsultaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private transient Consulta consulta;
	private transient List<Consulta> listaConsultas;

	transient FacesUtil facesUtil = new FacesUtil();

	@Inject
	DialogBean dialogBean;

	public void listarConsultas() {
		this.listar();
	}

	public void novoConsulta() {
		consulta = new Consulta();
		this.openDlgConsulta();
	}

	public void editarConsulta(Consulta consulta) {
		this.consulta = consulta;
		this.openDlgConsulta();
	}

	public void salvarConsulta() {
		if (consulta.getId() == null) {

			// recupera o id do ultimo consulta.
			if (listaConsultas == null) {
				listaConsultas = new ArrayList<>();
			}
			int id = listaConsultas.size() + 1;
			consulta.setId(id);
			this.incluir(consulta);
		} else {
			this.alterar(consulta);
		}
		dialogBean.addActionMessage(facesUtil.getMessage("MGL025"), "consultaBean.closeDlgConsulta",
				":formListaConsultas:tableConsultas");
	}

	public void closeDlgConsulta() {
		PrimeFaces.current().executeScript("PF('dlgConsulta').hide()");
	}

	public void openDlgConsulta() {
		PrimeFaces.current().ajax().update("formConsulta");
		PrimeFaces.current().executeScript("PF('dlgConsulta').show()");
	}

	public void confirmarExclusao(Consulta consulta) {
		this.consulta = consulta;
		String[] params = { consulta.getDataConsulta() + "-" + consulta.getStatus() };
		dialogBean.addConfirmMessage(facesUtil.getMessage("MGL038", params), "consultaBean.excluirConsulta", null,
				":formListaConsultas:tableConsultas");
	}

	public void excluirConsulta() {
		this.excluir();
		String[] params = { consulta.getDataConsulta() + "-" + consulta.getStatus() };
		dialogBean.addMessage(facesUtil.getMessage("MGL039", params), DialogType.INFO_CLOSABLE);
	}

	/*-------
	 * DAO
	 -------*/
	public void redirectListaConsultas() {
		facesUtil.redirect("/pages/showcase/primefaces/crud/consultas");
	}

	public void listar() {
		if (listaConsultas == null) {
			listaConsultas = new ArrayList<>();
		}
		this.redirectListaConsultas();
	}

	void incluir(Consulta consulta) {
		listaConsultas = new ArrayList<>();
		listaConsultas.add(consulta);
	}

	void alterar(Consulta consulta) {
		// N�o faz nada, nesse exemplo...
	}

	void excluir() {
		listaConsultas.remove(consulta); // Remove o consulta da lista.
	}

}
