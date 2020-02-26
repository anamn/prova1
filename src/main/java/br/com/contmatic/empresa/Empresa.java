package br.com.contmatic.empresa;

import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.commons.lang3.builder.ToStringStyle.JSON_STYLE;

import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.URL;
import org.hibernate.validator.constraints.br.CNPJ;

import com.google.common.base.Preconditions;

import br.com.contmatic.endereco.Endereco;
import br.com.contmatic.financeiro.Lucro;
import br.com.contmatic.telefone.Telefone;

/**
 * The Class Empresa.
 */
public class Empresa {

    /** The nome. */
    @NotEmpty(message = "Nome invalido")
    @Pattern(regexp = "[aA-zZáÁ-úÚ0-9\\s]{2,50}", message = "Nome invalido")
    private String nome;

    /** The cnpj. */
    @CNPJ(message = "CNPJ invalido")
    @NotEmpty(message = "CNPJ invalido")
    private String cnpj;

    /** The telefones. */
    @Size(min = 1, max = 5, message = "É necessario ao menos um telefone no cadastro e no maximo cinco")
    private Set<Telefone> telefones;

    /** The email. */
    @NotEmpty(message = "Email invalido")
    @Pattern(regexp = "^([0-9a-z]+([_.-]?[0-9a-z]+)*@[a-z]+[a-z,.,-]*(.){1}[a-z]{2,4})+$", message = "Email invalido")
    private String email;

    /** The enderecos. */
    @Valid
    @Size(min = 1, message = "É necessario ao menos um endereço no cadastro")
    private Set<Endereco> enderecos;

    /** The clientes. */
    @Valid
    private Set<Cliente> clientes;

    /** The funcionarios. */
    @Valid
    private Set<Funcionario> funcionarios;

    /** The produtos. */
    @Valid
    private Set<Produto> produtos;

    /** The lucro. */
    @Valid
    private Lucro lucro;

    /** The site. */
    @URL(message = "Site invalido")
    private String site;

    /**
     * Instantiates a new empresa.
     *
     * @param nome the nome
     * @param cnpj the cnpj
     * @param telefones the telefones
     * @param email the email
     * @param endereco the endereco
     */
    public Empresa(String nome, String cnpj, Set<Telefone> telefones, String email, Set<Endereco> endereco) {
        super();
        this.nome = nome;
        this.cnpj = cnpj;
        this.telefones = telefones;
        this.email = email;
        this.enderecos = endereco;
        
    }

    /**
     * Instantiates a new empresa.
     */
    public Empresa() {
        super();
    }

    /**
     * Valida cliente.
     *
     * @param cliente the cliente
     * @return true, if successful
     */
    public boolean validaCliente(Cliente cliente) {
        checkArgument(clientes.contains(cliente), new IllegalArgumentException("Cliente não encontrado"));
        return true;
    }

    /**
     * Valida funcionario.
     *
     * @param funcionario the funcionario
     * @return true, if successful
     */
    public boolean validaFuncionario(Funcionario funcionario) {
        Preconditions.checkArgument(funcionarios.contains(funcionario), new IllegalArgumentException("Funcionario não encontrado"));
        return true;
    }

    /**
     * Gets the lucro.
     *
     * @return the lucro
     */
    public Lucro getLucro() {
        return lucro;
    }

    /**
     * Sets the lucro.
     *
     * @param lucro the new lucro
     */
    public void setLucro(Lucro lucro) {
        this.lucro = lucro;
    }

    /**
     * Sets the nome.
     *
     * @param nome the new nome
     */
    public void setNome(String nome) {
        this.nome = nome;

    }

    /**
     * Gets the site.
     *
     * @return the site
     */
    public String getSite() {
        return site;
    }

    /**
     * Sets the site.
     *
     * @param site the new site
     */
    public void setSite(String site) {
        this.site = site;
    }

    /**
     * Gets the nome.
     *
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * Sets the cnpj.
     *
     * @param cnpj the new cnpj
     */
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    /**
     * Gets the cnpj.
     *
     * @return the cnpj
     */
    public String getCnpj() {
        return cnpj;
    }

    /**
     * Gets the enderecos.
     *
     * @return the enderecos
     */
    public Set<Endereco> getEnderecos() {
        return enderecos;
    }

    /**
     * Sets the enderecos.
     *
     * @param endereco the new enderecos
     */
    public void setEnderecos(Set<Endereco> endereco) {
        this.enderecos = endereco;
    }

    /**
     * Gets the telefones.
     *
     * @return the telefones
     */
    public Set<Telefone> getTelefones() {
        return telefones;
    }

    /**
     * Sets the telefones.
     *
     * @param telefones the new telefones
     */
    public void setTelefones(Set<Telefone> telefones) {
        this.telefones = telefones;
    }

    /**
     * Gets the email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email.
     *
     * @param email the new email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the clientes.
     *
     * @return the clientes
     */
    public Set<Cliente> getClientes() {
        return clientes;
    }

    /**
     * Sets the clientes.
     *
     * @param clientes the new clientes
     */
    public void setClientes(Set<Cliente> clientes) {
        this.clientes = clientes;
    }

    /**
     * Gets the funcionarios.
     *
     * @return the funcionarios
     */
    public Set<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    /**
     * Sets the funcionarios.
     *
     * @param funcionarios the new funcionarios
     */
    public void setFuncionarios(Set<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    /**
     * Gets the produtos.
     *
     * @return the produtos
     */
    public Set<Produto> getProdutos() {
        return produtos;
    }

    /**
     * Sets the produtos.
     *
     * @param produtos the new produtos
     */
    public void setProdutos(Set<Produto> produtos) {
        this.produtos = produtos;
    }

    /**
     * To string.
     *
     * @return the string
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, JSON_STYLE);
    }

    /**
     * Hash code.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(cnpj).toHashCode();
    }

    /**
     * Equals.
     *
     * @param obj the obj
     * @return true, if successful
     */
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
