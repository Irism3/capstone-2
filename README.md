# 🌮Yummy-Taco 
Hi! This is my Java console application for Yummy-Taco, where customers can build their order with drinks, chips & salsa, and tacos. I wanted this project 
to feel simple, organized, and easy to understand while showing the OOP concepts I’ve been learning.
# 🧠 OOP Concepts I Used

I built this using the main OOP ideas we learned:

- Abstraction: OrderItem is my abstract class

- Inheritance: Drink, ChipsAndSalsa, and Taco extend OrderItem

- Polymorphism: My Order class stores different items in one list

- Encapsulation: Private fields + getters

I made sure each class has a clear purpose so it’s easier to update or add new menu items later.
# 🧾 What My App Can Do

Here’s a quick breakdown of what the program lets you do:

### 🥤 Drinks

Choose a drink size (Small, Medium, Large)

Pick a flavor (Horchata, Jamaica, Coke, Sprite, Water)

The summary will show both size and flavor

### 🍟 Chips & Salsa

Choose salsa: Salsa Verde, Roja, Chipotle, Habanero, Mild, Extra Hot

Always $1.50

Shows up in the order summary like:
Chips & Salsa (Salsa Roja) – $1.50

### 🌮 Tacos

(If you’re still working on this part, you can update it later.)

### 🧺 Order Summary

Shows everything you picked

Clean, formatted line for each item

Adds up the total at the end

Example:
## 🧾 Example Receipt 

```text
===== Order Summary =====
Drink (Small, Jamaica) - $2.00
Chips & Salsa (Salsa Roja) - $1.50
Total: $3.50
```
