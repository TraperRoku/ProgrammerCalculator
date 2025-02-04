# ProgrammerCalculator
## A Windows Programmer Calculator Clone
![image](https://github.com/user-attachments/assets/44869005-1920-433e-ba28-620d98489a27)
![image](https://github.com/user-attachments/assets/2c56ce67-096c-43ff-9054-62579d24dce9)
 

# Programmer Calculator

A Windows Programmer Calculator Clone

## Features

- Data Input
- Performing Calculations
- Outputting Results
- Binary Representation of Results
- Number System and Bit-Length Selection
- Logical Gates
- Bitwise Shifting
- Calculator Memory Management

---

### **Functionality: Data Input**

#### Description
Allows the user to input data into the calculator, specifying valid characters and input methods.

#### Input
- Number system used to determine valid characters.
- Numbers compatible with the selected system.
- Arithmetic symbols allowed for calculations.

#### Output
- Each number is displayed temporarily in the result window.
- Arithmetic symbols should not appear in the result window.

#### Additional Notes
- Users cannot input numbers exceeding the space limit set by the user.

---

### **Functionality: Performing Calculations**

#### Description
Calculations are performed after pressing the execute button, following the correct order of operations.

#### Input
- All entered data in the order it was inputted.

#### Output
- The result of the calculations, compliant with the order of operations.

#### Additional Notes
- Users can use parentheses to enforce a specific order of operations.

---

### **Functionality: Outputting Results**

#### Description
The result should be displayed in the chosen number system.

#### Input
- Result from the calculation functionality.

#### Output
- The result displayed in the result window in the chosen number system.

#### Additional Notes
- The result should not exceed the space limit set by the user.

---

### **Functionality: Binary Representation of Results**

#### Description
The result is converted into a binary representation, modifiable by interacting with specific bits. The length is limited by the user-defined bit length.

#### Input
- The number currently displayed in the result window, whether final or in progress.

#### Output
- The binary representation shown in a separate window beneath the result window.

#### Additional Notes
- The binary representation allows individual bit editing. Clicking a bit toggles it and updates the final result.
- The first bit represents the sign for decimal systems.

---

### **Functionality: Number System and Bit-Length Selection**

#### Description
The user can select the number system and bit-length representation for all displayed data.

#### Input
- Radio buttons for number systems: Binary (Bin), Octal (Oct), Decimal (Dec), Hexadecimal (Hex).
- Radio buttons for bit lengths: QWord (8 bytes), DWord (4 bytes), Word (2 bytes), Byte.

#### Output
- Graphical representation of the selected system and bit-length.
- Buttons incompatible with the chosen system are disabled.

---

### **Functionality: Logical Gates**

#### Description
Logical gates `NOT`, `AND`, `OR`, `XOR` operate as per their computer science definitions.

#### Input
- `NOT`: A single number to negate.
- `AND`, `OR`, `XOR`: Two numbers to process.

#### Output
- Result of the logical gate operation.

---

### **Functionality: Bitwise Shifting**

#### Description
Buttons to perform bitwise shifts in specific directions:
- Left/Right Shift (Lossy): `Lsh`, `Rsh`
- Rotate Left/Right (Lossless): `RoL`, `RoR`

#### Input
- `RoL`/`RoR`: The number to shift.
- `Lsh`/`Rsh`: The number to shift and the number of positions to shift.

#### Output
- Updated result.

#### Additional Notes
- `RoL` and `RoR` are lossless, rotating bits that shift beyond the available space to the other end.

---

### **Functionality: Calculator Memory**

#### Description
Memory buttons for saving and retrieving the currently displayed value:
- `MC` (Memory Clear): Clears memory.
- `MR` (Memory Recall): Loads from memory.
- `MS` (Memory Store): Saves to memory.
- `M+` (Memory Add): Adds a number to the memory value.
- `M-` (Memory Subtract): Subtracts a number from the memory value.

#### Input
- A number (except for `MC` and `MR`).

#### Output
- For `MR`: The number stored in memory.

#### Additional Notes
- Displays an `M` symbol when a value is stored in memory.


 
