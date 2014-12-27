package com.programmingfree.springservice.greek.model;

import com.programmingfree.springservice.Lexeme;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;

@Entity
@Table(name="dictionary", catalog="clovisdb") // rename catalog to greek , i.e. <LANG>
public class GreekLexeme extends Lexeme {

	private String constr;
	private String info;
	private String stem;
	private String irreg;
	private String variant;


	public String getConstr() {
		return constr;
	}
	public void setConstr(String constr) {
		this.constr = constr;
	}

	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}

	public String getStem() {
		return stem;
	}
	public void setStem(String stem) {
		this.stem = stem;
	}

	public String getIrreg() {
		return irreg;
	}
	public void setIrreg(String irreg) {
		this.irreg = irreg;
	}

	public String getVariant() { return variant; }
	public void setVariant(String variant) {
		this.variant = variant;
	}


	@Override
	public String toString() {
		return "GreekLexeme{#"+ id +
				", lex='" + lex + '\'' +
				", pars='" + pars + '\'' +
				", constr='" + constr + '\'' +
				", info='" + info + '\'' +
				", stem='" + stem + '\'' +
				", irreg='" + irreg + '\'' +
				", variant='" + variant + '\'' +
				", semanid='" + semanid + '\'' +
				", def='" + def + '\'' +
				", etym='" + etym + '\'' +
				", syntax='" + syntax + '\'' +
				", freq=" + freq +
				'}';
	}
}

