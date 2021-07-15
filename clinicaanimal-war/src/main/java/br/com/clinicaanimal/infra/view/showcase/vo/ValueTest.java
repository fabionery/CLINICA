package br.com.clinicaanimal.infra.view.showcase.vo;

import java.util.Date;

import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import br.com.clinicaanimal.component.pworld.domain.AbstractEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class ValueTest extends AbstractEntity<Long> {

	private static final long serialVersionUID = -2413164380798986122L;

	private Long id;
	private Integer intValue; // .............integer normal
	private Integer intValue4; // ............integer com limitacao de 4 digitos
	private Long longValue; // ...............long normal
	private Double doubleValue; // ...........double normal contendo varias casas decimais
	private Double moeda; // .................double com apenas duas casas decimais (moeda)
	private Date dateValue; // ...............campo data normal Date()
	private String strValue6; // .............String com limitacao de 6 caracteres
	private String strValue; // ..............String sem limites
	private String strValueNoSpaceBlank; // ..String nao admite espaco em branco

	@CPF
	private String cpf;

	@CNPJ
	private String cnpj;

	private String email;
	private String tel;
	private String cep;

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getIntValue() {
		return intValue;
	}

	public void setIntValue(Integer intValue) {
		this.intValue = intValue;
	}

	public Integer getIntValue4() {
		return intValue4;
	}

	public void setIntValue4(Integer intValue4) {
		this.intValue4 = intValue4;
	}

	public Long getLongValue() {
		return longValue;
	}

	public void setLongValue(Long longValue) {
		this.longValue = longValue;
	}

	public Double getDoubleValue() {
		return doubleValue;
	}

	public void setDoubleValue(Double doubleValue) {
		this.doubleValue = doubleValue;
	}

	public Double getMoeda() {
		return moeda;
	}

	public void setMoeda(Double moeda) {
		this.moeda = moeda;
	}

	public Date getDateValue() {
		return dateValue;
	}

	public void setDateValue(Date dateValue) {
		this.dateValue = dateValue;
	}

	public String getStrValue6() {
		return strValue6;
	}

	public void setStrValue6(String strValue6) {
		this.strValue6 = strValue6;
	}

	public String getStrValue() {
		return strValue;
	}

	public void setStrValue(String strValue) {
		this.strValue = strValue;
	}

	public String getStrValueNoSpaceBlank() {
		return strValueNoSpaceBlank;
	}

	public void setStrValueNoSpaceBlank(String strValueNoSpaceBlank) {
		this.strValueNoSpaceBlank = strValueNoSpaceBlank;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

}
