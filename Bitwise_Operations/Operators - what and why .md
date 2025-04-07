![bitwise_ops_table](https://github.com/user-attachments/assets/b74599cd-fadc-42f5-bdff-33efca9aae46)
Source: Open Genius: https://iq.opengenus.org/addition-using-bitwise-operations/

1. AND operator (&)
   - Returns a 1 bit in the position where both operands contain a 1.
   - Useful for: creating a BITMASK to check if certain bits are set, checking if a number is even or odd (parity),
   - EX: 12 & 9 -> `(1100) & (1001) = 8 (1000)`
   - Parity: Using [&1] to only check the last bit - `if (8 & 1) == 0 return: 8 is even` (1000) & (0001) = 000_0
   - BITMASK: If we want to check a large number of bits at once such as lower 16 bits of a 32 bit integer: `BITMASK = 0xffff (16 unset bits)+(16 set bits), Integer.MAX_VALUE & BITMASK = 0000 0000 0000 0000 1111 1111 1111 1111`
   - BITMASK 2: We can bitmask to check if a certain bit is set such as the 7th bit (0-indexed): `BITMASK = 1 <<< 7    132 (10000100) & BITMASK = 10000000` (yes, the 7th bit is set)
  
2. OR operator (|)
   - Returns a 1 in the bit position where at least one of the numbers contains a set bit - BitA contains a 1 `OR` BitB contains a 1
   -  Useful for: A different kind of BITMASK where you can change a single bit without affecting the other bits, Permissions commands / storing permissions (RWX), Storing two numbers into a single integer (if they are small enough)
   -  Single change BITMASK: `BITMASK = 0100, Num = 1000 -> Num = (Num | BITMASK)` Num = 1100. Even if bit at index 2 was already set such as (1100) | (0100) it will still return 1100... doesn't affect other set bits
   -  Permissions: Read = 4, Write = 2, Execute = 1. User1 should be able to read and write. `User1_permissions = Read | Write` = 6.
   -  Storing multiple numbers in a single number: `int a = 1010 (10) int b = 1001 (9). int merged = ( (a << 8) | b`  ->  `merged = 1010 0000 0000 1001  a = high 4 bits, b = low 4 bits with 8 padded 0 bits between them`
  
3. NOT operator or 1's complement (~)
   - Returns the complete opposite (all flipped) bit representation of a number.
   - Be careful to make sure you know how the sign bit works and whether or not you are using signed or unsigned integers because flipping the sign bit will make a positive number negative
   - Useful for: Extracting a specific bit or group of bits, Getting 2's complement of a number, Setting Specific Bits to 0
   - Extracting bits: Extracting the first bit - `x = ~(x << 1) ` Extracting a range of bits - Extract bits 4-7: `MASK = 1111 0000 num = 1011011101011101  result = (num & MASK) >> 4`
   - 2's Complement / pure negation: `Num = 0 1101 0011 num = ~Num + 1  Num = 1 0010 1101`
   - Setting specific bits to 0 (unset): `x = x & ~(1 << 5)` will make the 5th bit a 0 if it was set.

4. XOR operator (^)
   - Returns a number with set bits only where bits differ between the two numbers
   - Useful for: Everything - Swapping two numbers without a third var, finding a unique element from a list of numbers, finding a missing number from a sorted list, additional bit manipulation tricks
   - Swapping two numbers: `a = 4, b = 2 ->  a ^= b, b ^= a, a^= b` a = 2, b = 4 after the three operations
   - Find unique element from list: `arr={1,4,8,4,1}, xor = 1 -> for: num in arr { xor ^= num} return xor` xor = 8 the only unique element
   - Find missing number: `arr = {0, 1, 3, 4}, missing = 0 -> for: i = list start to list end {missing ^= i ^ arr[i]} missing ^= arr[list end] return missing` missing = 2
   - Additonal tricks:: check if 2 nums (a and b) differ by x bits: `if ((a ^ b) & ((a ^ b) - x) == 0) return true`, Toggle the ith bit in a: `a ^= (1 << i)`

5. Left Shift (<<)
   - Shifts the bits to the left in a number by a specified amount filing the space left with 0's
   - Useful in tandem with all the other operators, is used to mask certain bits, multiply by powers of 2
   - Masking: Set 5th bit in x: `x |= (1 << 4)`, Clear 5th bit in x: `x &= ~(1 << 4)`, Extract bits 4,5,6,7 from x: `(x >> 4) & 0xF`
   - Multiply by power of 2: `x = 8, 8 * 8 -> x <<= 3` x = 64
     
7. Right Shift (>>) / (>>>)
   - Shifts the bits to the right in a number by a specified amount filing the space left with 0's if the shift is signed (>>) or with the same as the sign bit if unsigned shift (>>>)
   - Useful in tandem with other operators and similar use to left just for the opposite direction: counting set bits, dividing by powers of 2
   - Counting set bits: `while (x != 0) { bits += (x & 1), x >>= 1` adds one to count if the last bit is set, and then pops the last bit off by pushing all others right by one
   - Dividing by powers of 2: `x = 24, x / 8 -> x >>= 3` x = 3
