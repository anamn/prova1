package br.com.contmatic.empresa;

import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;
import static org.apache.commons.lang3.builder.ToStringStyle.JSON_STYLE;

import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.br.CPF;


public class Cliente {

    @NotEmpty(message = "Nome invalido")
    @Pattern(regexp = "[aA-zZ,áÁ-úÚ]{2,50}", message = "Nome invalido")
    private String nome;

    @NotEmpty(message = "Cpf invalido")
    @CPF(message = "CPF invalido")
    private String cpf;

    @Valid  
    @Size(min = 1, max = 5, message = "É necessario ao menos um telefone no cadastro e no maximo cinco")
    private Set<Telefone> telefones;

    @NotNull(message = "Email invalido")
    @Email(message = "Email invalido")
    private String email;

    @Valid
    private Endereco endereco;

    public Cliente(String nome, String cpf, Set<Telefone> telefones, String email, Endereco endereco) {
        super();
        this.nome = nome;
        this.cpf = cpf;
        this.telefones = telefones;
        this.endereco = endereco;
        this.email = email;
    }

    public Cliente() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Set<Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones(Set<Telefone>telefones) {
        this.telefones = telefones;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return reflectionToString(this, JSON_STYLE);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(cpf).toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Cliente other = (Cliente) obj;
        return new EqualsBuilder().append(other.cpf, cpf).isEquals();

    }

}
