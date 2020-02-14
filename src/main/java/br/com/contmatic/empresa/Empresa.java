package br.com.contmatic.empresa;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.URL;
import org.hibernate.validator.constraints.br.CNPJ;

import com.google.common.base.Preconditions;


public class Empresa {

    @NotEmpty(message = "Nome invalido")
    @Pattern(regexp = "[^\\d]{1,50}", message = "Nome invalido")
    private String nome;

    @NotEmpty(message = "CNPJ invalido")
    @CNPJ(message = "CNPJ invalido")
    private String cnpj;

    @Size(min = 1, max = 5, message = "É necessario ao menos um telefone no cadastro e no maximo cinco")
    private Set<Telefone> telefones;

    @NotEmpty(message = "Email invalido")
    @Email(message = "Email invalido")
    private String email;

    @Valid
    @Size(min = 1, message = "É necessario ao menos um endereço no cadastro")
    private Set<Endereco> enderecos;

    @Valid
    private Set<Cliente> clientes;

    @Valid
    private Set<Funcionario> funcionarios;

    @Valid
    private Set<Produto> produtos;

    @Valid
    private Lucro lucro;

    @URL(message = "Site invalido")
    private String site;

    public Empresa(String nome, String cnpj, Set<Telefone> telefones, String email, Set<Endereco> endereco) {
        super();
        this.nome = nome;
        this.cnpj = cnpj;
        this.telefones = telefones;
        this.email = email;
        this.enderecos = endereco;
        
    }

    public Empresa() {
        super();
    }

    public boolean validaCliente(Cliente cliente) {
        checkArgument(clientes.contains(cliente), new IllegalArgumentException("Cliente não encontrado"));
        return true;
    }

    public boolean validaFuncionario(Funcionario funcionario) {
        Preconditions.checkArgument(funcionarios.contains(funcionario), new IllegalArgumentException("Funcionario não encontrado"));
        return true;
    }

    public Lucro getLucro() {
        return lucro;
    }

    public void setLucro(Lucro lucro) {
        this.lucro = lucro;
    }

    public void setNome(String nome) {
        this.nome = nome;

    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getNome() {
        return nome;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCnpj() {
        return cnpj;
    }

    public Set<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(Set<Endereco> endereco) {
        this.enderecos = endereco;
    }

    public Set<Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones(Set<Telefone> telefones) {
        this.telefones = telefones;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(Set<Cliente> clientes) {
        this.clientes = clientes;
    }

    public Set<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(Set<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    public Set<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(Set<Produto> produtos) {
        this.produtos = produtos;
    }

    @Override
    public String toString() {
        return new StringBuilder("Empresa:").append(nome).append(", CNPJ: ").append(cnpj).append(", Telefone: ").append(telefones).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(cnpj).toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Empresa other = (Empresa) obj;
        return new EqualsBuilder().append(other.cnpj, cnpj).isEquals();
    }
}
