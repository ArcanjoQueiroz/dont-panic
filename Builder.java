import java.io.Serializable;

public class Builder {

  static class Customer implements Serializable {

    private static final long serialVersionUID = -8831077969521921232L;

    private Long id;
    private String name;
    private String gender;
    private Address address;

    public Customer() {
    }

    private Customer(final Long id, final String name, final String gender, final Address address) {
      this.id = id;
      this.name = name;
      this.gender = gender;
      this.address = address;
    }

    public static CustomerBuilder builder() {
      return new CustomerBuilder();
    }

    public Long getId() {
      return id;
    }

    public void setId(Long id) {
      this.id = id;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getGender() {
      return gender;
    }

    public void setGender(String gender) {
      this.gender = gender;
    }

    public Address getAddress() {
      return address;
    }

    public void setAddress(Address address) {
      this.address = address;
    }

    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((id == null) ? 0 : id.hashCode());
      return result;
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj)
        return true;
      if (obj == null)
        return false;
      if (getClass() != obj.getClass())
        return false;
      Customer other = (Customer) obj;
      if (id == null) {
        if (other.id != null)
          return false;
      } else if (!id.equals(other.id))
        return false;
      return true;
    }

    @Override
    public String toString() {
      return "Customer [name=" + name + "]";
    }

    /**
     * This is a real builder
     */
    static class CustomerBuilder {
      private Long id;
      private String name;
      private String gender;
      private String street;
      private String city;
      private String state;
      private String country;

      /**
       * It is important to use the correct modifier in your code
       */
      private CustomerBuilder() {
      }

      public CustomerBuilder id(final Long id) {
        this.id = id;
        return this;
      }

      public CustomerBuilder name(final String name) {
        this.name = name;
        return this;
      }

      public CustomerBuilder gender(final String gender) {
        this.gender = gender;
        return this;
      }

      public CustomerBuilder street(final String street) {
        this.street = street;
        return this;
      }

      public CustomerBuilder city(final String city) {
        this.city = city;
        return this;
      }

      public CustomerBuilder state(final String state) {
        this.state = state;
        return this;
      }

      public CustomerBuilder country(final String country) {
        this.country = country;
        return this;
      }

      public Customer build() {
        /* Look, the build method uses the private constructors */
        return new Customer(this.id, this.name, this.gender,
            new Address(this.street, this.city, this.state, this.country));
      }
    }

  }

  static class Address implements Serializable {

    private static final long serialVersionUID = 8933169689402786683L;

    private String street;
    private String city;
    private String state;
    private String country;

    public Address() {
    }

    Address(final String street, final String city, final String state, final String country) {
      this.street = street;
      this.city = city;
      this.state = state;
      this.country = country;
    }

    public String getStreet() {
      return street;
    }

    public void setStreet(String street) {
      this.street = street;
    }

    public String getCity() {
      return city;
    }

    public void setCity(String city) {
      this.city = city;
    }

    public String getState() {
      return state;
    }

    public void setState(String state) {
      this.state = state;
    }

    public String getCountry() {
      return country;
    }

    public void setCountry(String country) {
      this.country = country;
    }

    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((city == null) ? 0 : city.hashCode());
      result = prime * result + ((country == null) ? 0 : country.hashCode());
      result = prime * result + ((state == null) ? 0 : state.hashCode());
      result = prime * result + ((street == null) ? 0 : street.hashCode());
      return result;
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj)
        return true;
      if (obj == null)
        return false;
      if (getClass() != obj.getClass())
        return false;
      Address other = (Address) obj;
      if (city == null) {
        if (other.city != null)
          return false;
      } else if (!city.equals(other.city))
        return false;
      if (country == null) {
        if (other.country != null)
          return false;
      } else if (!country.equals(other.country))
        return false;
      if (state == null) {
        if (other.state != null)
          return false;
      } else if (!state.equals(other.state))
        return false;
      if (street == null) {
        if (other.street != null)
          return false;
      } else if (!street.equals(other.street))
        return false;
      return true;
    }

    @Override
    public String toString() {
      return "Address [city=" + city + ", country=" + country + ", state=" + state + ", street=" + street + "]";
    }

  }

  public static void main(String[] args) {
    final Customer customer = Customer.builder().id(1L).name("Alexandre").gender("Male").street("Paulista Avenue")
        .city("Sao Paulo").state("SP").country("Brazil").build();
    System.out.println(customer);
  }
}
