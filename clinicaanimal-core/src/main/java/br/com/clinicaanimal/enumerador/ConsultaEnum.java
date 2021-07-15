package br.com.clinicaanimal.enumerador;

public enum ConsultaEnum {

	PUBLICA("publica"), PRIVADA("privada");

	private final String status;

	private ConsultaEnum(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public static ConsultaEnum obterPorStatus(String status) {
		for (ConsultaEnum consultaEnum : values()) {
			if (consultaEnum.status.equals(status)) {
				return consultaEnum;
			}
		}
		return null;
	}

}
