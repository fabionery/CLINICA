package br.com.clinicaanimal.infra.view.showcase;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.clinicaanimal.component.dialog.DialogBean;
import br.com.clinicaanimal.component.upload.Upload;
import br.com.clinicaanimal.component.upload.UploadMode;
import br.com.clinicaanimal.infra.util.FileUtil;
import lombok.Getter;
import lombok.Setter;

@Named
@ViewScoped
@Getter
@Setter
public class UploadCaseBean implements Serializable {

	private static final long serialVersionUID = -8702542368264033202L;
	private static final String FILES = "files";

	@Inject
	DialogBean dialogBean;

	// Usar com fileUpload.
	private String fullPath;
	private Upload upload;
	private String allowTypes;

	// Inicializa o componente Upload em modo FILE.
	public void chamarUpload() {

		/*
		 * Os arquivos vao para, neste caso, o diretorio C do meu computador. Lembrando
		 * que o meu computador (localhost) tambem eh o servidor. Voce deve planejar bem
		 * o local dos arquivos recebidos, em caso de servidror remoto
		 */
		String servidor = "/"; // se servidor remoto - considere ter que digitar o IP aqui...

		// tipos de arquivos suportados pelo componente NESTE TESTE.
		allowTypes = "/(\\.|\\/)(gif|jpe?g|png|pdf|zip|xml|xhtml|html|css|txt|mp3|doc|doc|xls|xlsx|avi)$/";

		String resourcePath = servidor; // no meu caso tenho um diretorio "C".
		if (!new FileUtil().isExisteFileOrDir(resourcePath + FILES)) { // local dos arquivos -D:/files
			new FileUtil().criarDiretorio(resourcePath + FILES);
		}
		fullPath = resourcePath + FILES;
		upload = new Upload(dialogBean, UploadMode.FILE_SERVER, fullPath, false);
	}

	public String getFullPath() {
		return fullPath;
	}

	public void setFullPath(String fullPath) {
		this.fullPath = fullPath;
	}

	public Upload getUpload() {
		return upload;
	}

	public void setUpload(Upload upload) {
		this.upload = upload;
	}

	public String getAllowTypes() {
		return allowTypes;
	}

	public void setAllowTypes(String allowTypes) {
		this.allowTypes = allowTypes;
	}

}
