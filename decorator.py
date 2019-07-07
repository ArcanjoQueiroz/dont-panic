#!/usr/bin/env python3
# -*- coding: utf-8 -*-

# Beverages

class Beverage:
  def getDescription(self):
    pass
  def cost(self):
    pass


class Expresso(Beverage):
  def getDescription(self):
    return "Expresso"

  def cost(self):
    return 2.00


class DarkRoat(Beverage):
  def getDescription(self):
    return "Dark Roat"

  def cost(self):
    return 3.5


class HouseBlend(Beverage):
  def getDescription(self):
    return "House Blend"

  def cost(self):
    return 4.0

# Condiments

class CondimentDecorator(Beverage):
  pass

class Soy(CondimentDecorator):
  def __init__(self, beverage):
    self.beverage = beverage

  def getDescription(self):
    return "{0}, {1}".format(self.beverage.getDescription(), "Soy")

  def cost(self):
    return self.beverage.cost() + 0.10


class Mocha(CondimentDecorator):
  def __init__(self, beverage):
    self.beverage = beverage

  def getDescription(self):
    return "{0}, {1}".format(self.beverage.getDescription(), "Mocha")

  def cost(self):
    return self.beverage.cost() + 0.50


class Whip(CondimentDecorator):
  def __init__(self, beverage):
    self.beverage = beverage

  def getDescription(self):
    return "{0}, {1}".format(self.beverage.getDescription(), "Whip")

  def cost(self):
    return self.beverage.cost() + 0.45


if __name__ == '__main__':
  # A simple expresso
  expresso = Expresso()
  print("{0} \t\t\t$ {1}".format(expresso.getDescription(), expresso.cost()))

  # Decorator example: A Dark Roat with double Mocha and Whip
  dark_roat = Whip(Mocha(Mocha(DarkRoat())))
  print("{0} \t$ {1}".format(dark_roat.getDescription(), dark_roat.cost()))

  # Decorator example: A House Bend with Soy, Mocha and Whip
  house_blend = Whip(Mocha(Soy(HouseBlend())))
  print("{0} \t$ {1}".format(house_blend.getDescription(), house_blend.cost()))