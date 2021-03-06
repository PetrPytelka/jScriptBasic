'
' This program is used to test the functionality of the
' command select case ... end select
'
v = "1"

' Test 'select' with 'case' keyword
select case v
case "0": print "0"
case "1": print "1"
case "2": print "2"
end select

' Test 'select' without matching case
select case v
case "0": print "0"
case "2": print "2"
end select

' Test expression in 'select'
' and 'case else' statement
select v+"1"
case "1": print "0"
case "2", "11": print "1"
case else: print "2"
end select

' Test 'case else' statement usage
select v
case "0": print "0"
case else: print "1"
end select

' Test 'case' with 'to' statement
select case v
case "-": print "-"
case "0" to "2": print "1"
case "3": print "3"
end select

' Test case with 'is' statement
select case v
case is "0": print "0"
case is "1": print "1"
case is "2": print "2"
end select

' Test comments inside select case
select case v
rem comment before first case
case "0": print "0"
rem comment between case
case "1": print "1"
case "2": print "2"
rem comment before end select
end select
rem comment after end select

print "1"
