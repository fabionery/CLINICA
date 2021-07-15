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
import br.com.clinicaanimal.infra.view.showcase.vo.Veterinario;
import lombok.Getter;
import lombok.Setter;

@Named
@ViewScoped
@Getter
@Setter
public class VeterinarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private transient Veterinario veterinario;
	private transient List<Veterinario> listaVeterinarios;

	transient FacesUtil facesUtil = new FacesUtil();

	@Inject
	DialogBean dialogBean;

	public void listarVeterinarios() {
		this.listar();
	}

	public void novoVeterinario() {
		veterinario = new Veterinario();
		this.openDlgVeterinario();
	}

	public void editarVeterinario(Veterinario veterinario) {
		this.veterinario = veterinario;
		this.openDlgVeterinario();
	}

	public void salvarVeterinario() {
		if (veterinario.getId() == null) {

			// recupera o id do ultimo veterinario.
			if (listaVeterinarios == null) {
				listaVeterinarios = new ArrayList<>();
			}
			int id = listaVeterinarios.size() + 1;
			veterinario.setId(id);
			this.incluir(veterinario);
		} else {
			this.alterar(veterinario);
		}
		dialogBean.addActionMessage(facesUtil.getMessage("MGL025"), "veterinarioBean.closeDlgVeterinario",
				":formListaVeterinarios:tableVeterinarios");
	}

	public void closeDlgVeterinario() {
		PrimeFaces.current().executeScript("PF('dlgVeterinario').hide()");
	}

	public void openDlgVeterinario() {
		PrimeFaces.current().ajax().update("formVeterinario");
		PrimeFaces.current().executeScript("PF('dlgVeterinarior').show()");
	}

	public void confirmarExclusao(Veterinario veterinario) {
		this.veterinario = veterinario;
		String[] params = { veterinario.getNome() + "/" + veterinario.getTelefone() + "-" + veterinario.getEmail() };
		dialogBean.addConfirmMessage(facesUtil.getMessage("MGL038", params), "veterinarioBean.excluirVeterinario", null,
				":formListaVeterinarios:tableVeterinarios");
	}

	public void excluirVeterinario() {
		this.excluir();
		String[] params = { veterinario.getNome() + "/" + veterinario.getTelefone() + "-" + veterinario.getEmail() };
		dialogBean.addMessage(facesUtil.getMessage("MGL039", params), DialogType.INFO_CLOSABLE);
	}

	/*-------
	 * DAO
	 -------*/
	public void redirectListaVeterinarios() {
		facesUtil.redirect("/pages/showcase/primefaces/crud/veterinarios");
	}

	public void listar() {
		if (listaVeterinarios == null) {
			listaVeterinarios = new ArrayList<>();
		}
		this.redirectListaVeterinarios();
	}

	void incluir(Veterinario veterinario) {
		listaVeterinarios = new ArrayList<>();
		listaVeterinarios.add(veterinario);
	}

	void alterar(Veterinario veterinario) {
		// N�o faz nada, nesse exemplo...
	}

	void excluir() {
		listaVeterinarios.remove(veterinario); // Remove o veterinario da lista.
	}

}
