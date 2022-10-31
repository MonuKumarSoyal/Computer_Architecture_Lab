	.data
a:
	10
	.text
main:
	load %x0, $a, %x4
	divi %x4, 2, %x5
	beq %x31, 0, evn
	addi %x0, 1, %x10
	end
evn:
	subi %x0, 1, %x10
	end