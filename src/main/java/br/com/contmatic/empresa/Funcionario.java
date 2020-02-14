package br.com.contmatic.empresa;

import java.math.BigDecimal;
import java.util.Set;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.br.CPF;

import br.com.contmatic.validador.PIS;

public class Funcionario {

    @DecimalMin(value = "1.00", message = "Salario invalido")
    private BigDecimal salario;

    @NotEmpty(message = "Pis invalido")
    @PIS
    private String pis;

    @NotEmpty(message = "Nome invalido")
    @Pattern(regexp = "[aA-zZ,áÁ-úÚ]{2,50}", message = "Nome invalido")
    private String nome;
    
    @NotNull(message = "CPF invalido")
    @CPF(message = "CPF invalido")
    private String cpf;

    @Size(min = 1, max = 5, message = "É necessario ao menos um telefone no cadastro e no maximo cinco")
    private Set<Telefone> telefones;

    private Endereco endereco;

    public Funcionario(String nome, String cpf, String pis, Set<Telefone> telefones, BigDecimal salario, Endereco endereco) {
        super();
        this.salario = salario;
        this.pis = pis;
        this.nome = nome;
        this.cpf = cpf;
        this.telefones = telefones;
        this.endereco = endereco;
    }

    public Funcionario() {
    }

    public void setNome(String nome) {
        this.nome = nome;

    }

    public String getNome() {
        return nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;

    }

    public String getCpf() {
        return cpf;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public void setTelefones(Set<Telefone> tel) {
        this.telefones = tel;

    }

    public Set<Telefone> getTelefones() {
        return telefones;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;

    }

    public void setPis(String pis) {
        this.pis = pis;

    }

    public String getPis() {
        return pis;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(pis).toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Funcionario other = (Funcionario) obj;
        return new EqualsBuilder().append(other.pis, pis).isEquals();

    }

}
