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
import br.com.clinicaanimal.infra.view.showcase.vo.Tutor;
import lombok.Getter;
import lombok.Setter;

@Named
@ViewScoped
@Getter
@Setter
public class TutorBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private transient Tutor tutor;
	private transient List<Tutor> listaTutores;

	transient FacesUtil facesUtil = new FacesUtil();

	@Inject
	DialogBean dialogBean;

	public void listarTutores() {
		this.listar();
	}

	public void novoTutor() {
		tutor = new Tutor();
		this.openDlgTutor();
	}

	public void editarTutor(Tutor tutor) {
		this.tutor = tutor;
		this.openDlgTutor();
	}

	public void salvarTutor() {
		if (tutor.getId() == null) {

			// recupera o id do ultimo tutor.
			if (listaTutores == null) {
				listaTutores = new ArrayList<>();
			}
			int id = listaTutores.size() + 1;
			tutor.setId(id);
			this.incluir(tutor);
		} else {
			this.alterar(tutor);
		}
		dialogBean.addActionMessage(facesUtil.getMessage("MGL025"), "tutorBean.closeDlgTutor",
				":formListaTutores:tableTutores");
	}

	public void closeDlgTutor() {
		PrimeFaces.current().executeScript("PF('dlgTutor').hide()");
	}

	public void openDlgTutor() {
		PrimeFaces.current().ajax().update("formTutor");
		PrimeFaces.current().executeScript("PF('dlgTutor').show()");
	}

	public void confirmarExclusao(Tutor tutor) {
		this.tutor = tutor;
		String[] params = { tutor.getNome() + "/" + tutor.getTelefone() + "-" + tutor.getEmail() };
		dialogBean.addConfirmMessage(facesUtil.getMessage("MGL038", params), "tutorBean.excluirTutor", null,
				":formListaTutores:tableTutores");
	}

	public void excluirTutor() {
		this.excluir();
		String[] params = { tutor.getNome() + "/" + tutor.getTelefone() + "-" + tutor.getEmail() };
		dialogBean.addMessage(facesUtil.getMessage("MGL039", params), DialogType.INFO_CLOSABLE);
	}

	/*-------
	 * DAO
	 -------*/
	public void redirectListaTutores() {
		facesUtil.redirect("/pages/showcase/primefaces/crud/tutores");
	}

	public void listar() {
		if (listaTutores == null) {
			listaTutores = new ArrayList<>();
		}
		this.redirectListaTutores();
	}

	void incluir(Tutor tutor) {
		listaTutores = new ArrayList<>();
		listaTutores.add(tutor);
	}

	void alterar(Tutor tutor) {
		// N�o faz nada, nesse exemplo...
	}

	void excluir() {
		listaTutores.remove(tutor); // Remove o tutor da lista.
	}

}
