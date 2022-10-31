	.data
a:
	10
	.text
main:
	load %x0, $a, %x3
	beq %x3, 1, notprime
	addi %x0, 2, %x4
	jmp gotoloop
gotoloop:
	beq %x4, %x3, prime
	div %x3, %x4, %x5
	beq %x31, 0, notprime
	addi %x4, 1, %x4
	jmp gotoloop
prime:
	addi %x0, 1, %x10
	end
notprime:
	subi %x0, 1, %x10
	end