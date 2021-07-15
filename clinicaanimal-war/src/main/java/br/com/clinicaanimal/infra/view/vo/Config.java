package br.com.clinicaanimal.infra.view.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Config {

	/*
	 * Nao eh uma entidade persistente. Um Value Object serve apenas para
	 * transportar dados entre as camadas.
	 */

	/*--------------
	 * Dados de Menu
	 -------------*/
	private String menuType;

	/*--------------
	 * Dados de skin
	 -------------*/
	private String skinFooter;
	private String skinBackground;
	private String skinImageLogo;
	private String skinLogo;
	private String skinTextLogo;
	private String skinColorTextLogo;
	private String skinTheme;
	private String skinAnimatedTop;
	private String skinAnimatedHtml;

	public Config() {
		// do not compliance
	}

	public String getMenuType() {
		return menuType;
	}

	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}

	public String getSkinFooter() {
		return skinFooter;
	}

	public void setSkinFooter(String skinFooter) {
		this.skinFooter = skinFooter;
	}

	public String getSkinBackground() {
		return skinBackground;
	}

	public void setSkinBackground(String skinBackground) {
		this.skinBackground = skinBackground;
	}

	public String getSkinImageLogo() {
		return skinImageLogo;
	}

	public void setSkinImageLogo(String skinImageLogo) {
		this.skinImageLogo = skinImageLogo;
	}

	public String getSkinLogo() {
		return skinLogo;
	}

	public void setSkinLogo(String skinLogo) {
		this.skinLogo = skinLogo;
	}

	public String getSkinTextLogo() {
		return skinTextLogo;
	}

	public void setSkinTextLogo(String skinTextLogo) {
		this.skinTextLogo = skinTextLogo;
	}

	public String getSkinColorTextLogo() {
		return skinColorTextLogo;
	}

	public void setSkinColorTextLogo(String skinColorTextLogo) {
		this.skinColorTextLogo = skinColorTextLogo;
	}

	public String getSkinTheme() {
		return skinTheme;
	}

	public void setSkinTheme(String skinTheme) {
		this.skinTheme = skinTheme;
	}

	public String getSkinAnimatedTop() {
		return skinAnimatedTop;
	}

	public void setSkinAnimatedTop(String skinAnimatedTop) {
		this.skinAnimatedTop = skinAnimatedTop;
	}

	public String getSkinAnimatedHtml() {
		return skinAnimatedHtml;
	}

	public void setSkinAnimatedHtml(String skinAnimatedHtml) {
		this.skinAnimatedHtml = skinAnimatedHtml;
	}

}
