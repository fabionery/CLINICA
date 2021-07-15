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
import br.com.clinicaanimal.infra.view.showcase.vo.Animal;
import lombok.Getter;
import lombok.Setter;

@Named
@ViewScoped
@Getter
@Setter
public class AnimalBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private transient Animal animal;
	private transient List<Animal> listaAnimais;

	transient FacesUtil facesUtil = new FacesUtil();

	@Inject
	DialogBean dialogBean;

	public void listarAnimais() {
		this.listar();
	}

	public void novoAnimal() {
		animal = new Animal();
		this.openDlgAnimal();
	}

	public void editarAnimal(Animal animal) {
		this.animal = animal;
		this.openDlgAnimal();
	}

	public void salvarAnimal() {
		if (animal.getId() == null) {

			// recupera o id do ultimo animal.
			if (listaAnimais == null) {
				listaAnimais = new ArrayList<>();
			}
			int id = listaAnimais.size() + 1;
			animal.setId(id);
			this.incluir(animal);
		} else {
			this.alterar(animal);
		}
		dialogBean.addActionMessage(facesUtil.getMessage("MGL025"), "animalBean.closeDlgAnimal",
				":formListaAnimais:tableAnimais");
	}

	public void closeDlgAnimal() {
		PrimeFaces.current().executeScript("PF('dlgAnimal').hide()");
	}

	public void openDlgAnimal() {
		PrimeFaces.current().ajax().update("formAnimal");
		PrimeFaces.current().executeScript("PF('dlgAnimal').show()");
	}

	public void confirmarExclusao(Animal animal) {
		this.animal = animal;
		String[] params = { animal.getNome() + "/" + animal.getEspecie() + "-" + animal.getRaca() };
		dialogBean.addConfirmMessage(facesUtil.getMessage("MGL038", params), "animalBean.excluirAnimal", null,
				":formListaAnimais:tableAnimais");
	}

	public void excluirAnimal() {
		this.excluir();
		String[] params = { animal.getNome() + "/" + animal.getEspecie() + "-" + animal.getRaca() };
		dialogBean.addMessage(facesUtil.getMessage("MGL039", params), DialogType.INFO_CLOSABLE);
	}

	/*-------
	 * DAO
	 -------*/
	public void redirectListaAnimais() {
		facesUtil.redirect("/pages/showcase/primefaces/crud/animais");
	}

	public void listar() {
		if (listaAnimais == null) {
			listaAnimais = new ArrayList<>();
		}
		this.redirectListaAnimais();
	}

	void incluir(Animal animal) {
		listaAnimais = new ArrayList<>();
		listaAnimais.add(animal);
	}

	void alterar(Animal animal) {
		// N�o faz nada, nesse exemplo...
	}

	void excluir() {
		listaAnimais.remove(animal); // Remove o animal da lista.
	}

}
