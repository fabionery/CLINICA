package br.com.clinicaanimal.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.clinicaanimal.model.base.AbstractEntity;

@Entity(name = "Consulta")
@Table(name = "CONSULTA", schema = "PETS")
@NamedQueries({ @NamedQuery(name = "Consulta.allConsulta", query = "select c from Consulta v order by v.id desc") })
public class Consulta extends AbstractEntity<Long> {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(initialValue = 1, name = "consulta_seq", schema = "PETS", sequenceName = "consulta_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "consulta_seq")
	@Column(name = "ID", columnDefinition = "NUMBER(19,0)")
	private Long id;

	@Temporal(value = TemporalType.DATE)
	@Column(name = "DATA_CONSULTA", nullable = false)
	private Date dataConsulta;

	@Column(name = "STATUS", columnDefinition = "VARCHAR2(20)")
	private String status;

	@ManyToOne(targetEntity = Animal.class, fetch = FetchType.EAGER, optional = true)
	@JoinColumn(name = "ID", columnDefinition = "NUMBER(19,0)", nullable = false, foreignKey = @ForeignKey(name = "animal_fkey"))
	private Animal animal;

	@ManyToOne(targetEntity = Veterinario.class, fetch = FetchType.EAGER, optional = true)
	@JoinColumn(name = "ID", columnDefinition = "NUMBER(19,0)", nullable = false, foreignKey = @ForeignKey(name = "veterinario_fkey"))
	private Veterinario veterinario;

	/*--------
	 * get/set
	 --------*/
	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataConsulta() {
		return dataConsulta;
	}

	public void setDataConsulta(Date dataConsulta) {
		this.dataConsulta = dataConsulta;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}

	public Veterinario getVeterinario() {
		return veterinario;
	}

	public void setVeterinario(Veterinario veterinario) {
		this.veterinario = veterinario;
	}

}
