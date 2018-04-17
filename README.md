# AndroidRetrofit
## Memory Consistency Errors
> _Memory consistency errors_ occur when different threads have inconsistent views of what should be the same data

#### happens-before relationship
> This relationship is simply a guarantee that memory writes by one specific statement are visible to another specific statement

There are several actions that create happens-before relationships. One of them is synchronization
https://docs.oracle.com/javase/tutorial/essential/concurrency/memconsist.html

## Race condition
Simple mean the error when we access to a variable from many thread at same time => we will receive wrong value

Check race condition in Software part in wiki (example 2 thread increase by 1 for same variable at same time)
https://en.wikipedia.org/wiki/Race_condition
## Atomic
> _atomic_ action is one that effectively happens all at once

example
- Reads and writes are atomic for reference variables and for most primitive variables (all types except  `long`  and  `double`).
- Reads and writes are atomic for _all_ variables declared `volatile` (_including_  `long` and `double` variables)

Note:
+) `i++` is not atomic because it don't happens all at once, it will happened 3 things sequence (read i from memory, increase i, write back to memory)
+) `long` is not volatile because if you run on 32bit CPU, when we write a value 64bit to `long`  it need to write 2 times (each time 32bit) => not atomic (more explain https://stackoverflow.com/a/15054186/5381331)

Atomic document: https://docs.oracle.com/javase/tutorial/essential/concurrency/atomic.html
## Volatile

As current understand, volatile do 3 things
#### 1) Guarantee visibility
> Guarantee that variable always read from main memory not from CPU cache

*Explain:*
In a multi thread application where the threads operate on non-volatile variables, each thread may copy variables from main memory into a CPU cache while working on them, for performance reasons.  When thread done, it will write value back to main memory.
If your computer (mobile device) contains more than one CPU, each thread may run on a different CPU. That means, that each thread may copy the variables into the CPU cache of different CPUs. Then when a thread change a variable value another thread **will not see this change** => some problem

Can see the simple example of stop thread
https://stackoverflow.com/a/130320/5381331

#### 2) Guarantee full visibility
Still don't understand clearly this past but it basic like
When you execute code that write 2 variable to memory (1 is volatile, 1 is none-volatile) it will guarantee that both of them is write to main memory

#### 3) Guarantee atomic access
Reads and writes are atomic for _all_ variables declared `volatile` (from atomic docs)

Example in `Singleton` design pattern, we use `volatile` keyword in `instance` variable because we want to guarantee that read before write finish.
As the answer here https://stackoverflow.com/a/7855774/5381331, when object create it will allocate the memory then start constructing => without volatile, maybe in some situation a Thread can receive the `instance`  which is not finished constructing (I think some people called that is half-initialization)

### AtomicLong, AtomicBoolean
volatile => guarantee visibility
synchronized => guarantee visibility and blocking

Currently I think `AtomicLong`, `AtomicBoolean` <=> synchronized


### Reference

http://tutorials.jenkov.com/java-concurrency/volatile.html