package br.com.clinicaanimal.enumerador;

public enum AnimalEnum {

	GATO("gato"), CACHORRO("cachorro");

	private final String especie;

	private AnimalEnum(String especie) {
		this.especie = especie;
	}

	public String getEspecie() {
		return especie;
	}

	public static AnimalEnum obterPorEspecie(String especie) {
		for (AnimalEnum animalEnum : values()) {
			if (animalEnum.especie.equals(especie)) {
				return animalEnum;
			}
		}
		return null;
	}

}
