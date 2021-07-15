package br.com.clinicaanimal.infra.view.vo;

import java.util.ArrayList;
import java.util.List;

import br.com.clinicaanimal.component.pworld.domain.AbstractEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@EqualsAndHashCode(callSuper = true)
public class Theme extends AbstractEntity<Long> {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private String value;
	private List<Theme> themes;

	public Theme(Long id, String name, String value) {
		this.id = id;
		this.name = name;
		this.value = value;
	}

	public List<Theme> getThemes() {
		themes = new ArrayList<>();
		themes.add(new Theme(0L, "Aristo", "aristo"));
		themes.add(new Theme(1L, "Nova-Light", "nova-light"));
		themes.add(new Theme(2L, "Nova-Dark", "nova-dark"));
		themes.add(new Theme(3L, "Nova-Colored", "nova-colored"));
		themes.add(new Theme(4L, "Luna-Blue", "luna-blue"));
		themes.add(new Theme(5L, "Luna-Amber", "luna-amber"));
		themes.add(new Theme(6L, "Luna-Green", "luna-green"));
		themes.add(new Theme(7L, "Luna-Pink", "luna-pink"));
		themes.add(new Theme(8L, "Omega", "omega"));
		return themes;
	}

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setThemes(List<Theme> themes) {
		this.themes = themes;
	}
}