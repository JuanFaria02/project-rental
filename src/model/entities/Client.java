package model.entities;


import java.io.Serializable;
import java.util.*;

public class Client implements Serializable {
    private Integer id;
    private String cpf;
    private String name;

    public Client(){
    }

    public Client(String name, String cpf) {
      if (!isValid(cpf)) {
            throw new InputMismatchException("CPF invalid!");
      }

        this.cpf = cpf;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(id, client.id) && Objects.equals(name, client.name);
    }


    private boolean isValid(String cpf) {
        List<Integer> cpfList = Arrays.asList(cpf.split(""))
                .stream()
                .map(n -> Integer.valueOf(n))
                .toList();


        int firstDigit = getDigit(cpfList, 10, 2);;
        int secondDigit = getDigit(cpfList, 11, 1);

        return firstDigit == cpfList.get(cpfList.size()-2) && secondDigit == cpfList.get(cpfList.size()-1);
    }


    private Integer getDigit(List<Integer> cpf, Integer temp, Integer sizeCutCpf) {
        Integer sumDigit = 0;
        for (int i = 0; i < cpf.size()-sizeCutCpf; i++) {
            sumDigit += cpf.get(i) * temp;
            temp--;
        }
        int digit = (sumDigit * 10) % 11;

        if (digit == 10) {
            digit = 0;
        }
        return digit;
    }


    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cpf='" + cpf + '\'' +
                '}';
    }
}
