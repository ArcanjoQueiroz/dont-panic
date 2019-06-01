#!/usr/bin/env python3
# -*- coding: utf-8 -*-

# When should we use Builder Design Pattern?
#   When we have more than three 'static variations' in a object creation
#   When it is necessary to isolate creational details
#   When the object creation is complex or the code is unreadable
class Customer:
    class CustomerBuilder:
        def __init__(self):
            self.name = None
            self.age = None
            self.gender = None
            self.street = None
            self.city = None

        def withName(self, name):
            # Creational details
            if not name is None:
                self.name = name
            return self

        def withAge(self, age):
            # Could be a simple validation
            if not age is None:
                self.age = age
            return self

        def withGender(self, gender):
            # Or a transformation
            if not gender is None:
                self.gender = gender
            return self

        def withStreet(self, street):
            # Or an if/else condition isolation for null values
            if not street is None:
                self.street = street
            return self

        def withCity(self, city):
            if not city is None:
                self.city = city
            return self

        def build(self):
            return Customer(self.name, self.age, self.gender, self.street, self.city)


    def __init__(self, name, age, gender, street, city):
        self.name = name
        self.age = age
        self.gender = gender
        self.street = street
        self.city = city

    def __str__(self):
        return self.name

    @staticmethod
    def builder():
        return Customer.CustomerBuilder()


class MyCustomer:
    def __init__(self, name=None, age=0, gender=None, street=None, city=None):
        self.name = name
        self.age = age
        self.gender = gender
        self.street = street
        self.city = city

    def __str__(self):
        return self.name

    
def main():
    # Simple creation, forget about kwargs in this example
    a = Customer('Bruno', 32, 'Male', 'Paulista Avenue, 1290', 'Sao Paulo')
    b = Customer('Camila', 30, 'Female', None, None)
    c = Customer('Tatiana', 29, 'Female', 'Paes de Barros Avenue, 1033', None)
    d = Customer('Felipe', None, 'Male', None, None)

    # Builder Design Pattern
    e = Customer.builder().withName('Marco').withAge(21).withGender('Male').build()

    # Builder Design Pattern could be unnecessary in a python environment
    f = MyCustomer(name='Alexandre', age=32, gender='Male')

    # Using dict and kwargs
    g = Customer(**{
        "name": "Renan",
        "age": 27,
        "gender": "Male",
        "street": "Houston Street, 777",
        "city": "New York"
    })

    # However, in Java is helpful

    print(a)
    print(b)
    print(c)
    print(d)
    print(e)
    print(f)
    print(g)

if __name__ == '__main__':
    main()
