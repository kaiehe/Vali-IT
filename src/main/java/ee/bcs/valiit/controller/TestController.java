package ee.bcs.valiit.controller;

import ee.bcs.valiit.tasks.Lesson1;
import ee.bcs.valiit.tasks.Lesson2;
import ee.bcs.valiit.tasks.Lesson2b;
import ee.bcs.valiit.tasks.Lesson2c;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    //http://localhost:8080/sample/helloworld/Ellinor?action=Tsau&action2=kallis
    @GetMapping("sample/helloworld/{nameInUrl}")
    public String helloWorld(@PathVariable("nameInUrl") String name,
                             @RequestParam("action") String a,
                             @RequestParam("action2") String b) {
        return a + " " + name + " " + b;
    }

    //http://localhost:8080/min/8/3
    @GetMapping("/min/{a}/{b}")
    public int min(@PathVariable("a") int a, @PathVariable("b") int b) {
        return Lesson1.min(a, b);
    }

    //http://localhost:8080/fibonacci/10
    @GetMapping("/fibonacci/{n}")
    public int fibonacci(@PathVariable("n") int n) {
        return Lesson2.fibonacci(n);
    }

    //http://localhost:8080/fibonacci2?n=8
    @GetMapping("/fibonacci2")
    public int fibonacci2(@RequestParam("n") int n) {
        return Lesson2.fibonacci(n);
    }

    //http://localhost:8080/max?a=10&b=8
    @GetMapping("/max")
    public int max(@RequestParam("a") int a, @RequestParam("b") int b) {
        return Lesson1.max(a, b);
    }

    //http://localhost:8080/abs/-5
    @GetMapping("/abs/{a}")
    public int abs(@PathVariable("a") int a) {
        return Lesson1.abs(a);
    }

    //http://localhost:8080/iseven?a=6
    @GetMapping("/iseven")
    public boolean isEven(@RequestParam("a") int a) {
        return Lesson1.isEven(a);
    }

    //http://localhost:8080/min3/3/6/9
    @GetMapping("/min3/{a}/{b}/{c}")
    public int min3(@PathVariable("a") int a, @PathVariable("b") int b, @PathVariable("c") int c) {
        return Lesson1.min3(a, b, c);
    }

    //http://localhost:8080/max3?a=3&b=6&c=9
    @GetMapping("/max3")
    public int max3(@RequestParam("a") int a, @RequestParam("b") int b, @RequestParam("c") int c) {
        return Lesson1.max3(a, b, c);
    }

    //http://localhost:8080/reversearray/1,2,3,4
    @GetMapping("/reversearray/{reverse}")
    public int[] reversearray(@PathVariable("reverse") int[] reverseArray) {
        return Lesson2.reverseArray(reverseArray);
    }

    //http://localhost:8080/evennumbers?n=5
    @GetMapping("/evennumbers")
    public int[] evenNumbers(@RequestParam("n") int n) {
        return Lesson2.evenNumbers(n);
    }

    //http://localhost:8080/minarray/2,8,90
    @GetMapping("minarray/{x}")
    public int min(@PathVariable("x") int[] x) {
        return Lesson2.min(x);
    }

    //http://localhost:8080/maxarray?x=5,6,9
    @GetMapping("/maxarray")
    public int max(@RequestParam("x") int[] x) {
        return Lesson2.max(x);
    }

    //http://localhost:8080/sumarray/5,6,7
    @GetMapping("sumarray/{x}")
    public int sum(@PathVariable("x") int[] x) {
        return Lesson2.sum(x);
    }

    //http://localhost:8080/samplearray
    @GetMapping("/samplearray")
    public int[] sampleArray() {
        return Lesson2b.sampleArray();
    }

    //http://localhost:8080/generatearray/7
    @GetMapping("generatearray/{n}")
    public int[] array(@PathVariable("n") int n) {
        return Lesson2b.generateArray(n);
    }

    //http://localhost:8080/decreasingarray?n=10
    @GetMapping("/decreasingarray")
    public int[] decreasingArray(@RequestParam("n") int n) {
        return Lesson2b.decreasingArray(n);
    }

    //http://localhost:8080/yl3/5
    @GetMapping("/yl3/{n}")
    public int[] yl3array(@PathVariable("n") int n) {
        return Lesson2b.yl3(n);
    }

    //http://localhost:8080/sequence?x=2&y=5
    @GetMapping("/sequence")
    public int sequence(@RequestParam("x") int x, @RequestParam("y") int y) {
        return Lesson2c.sequence3n(x, y);
    }

    //http://localhost:8080/sequencelength/5
    @GetMapping("/sequencelength/{x}")
    public int sequenceLength(@PathVariable("x") int x) {
        return Lesson2c.getSeqLength(x);
    }
    //http://localhost:8080/nextElement?x=5
    @GetMapping("/nextElement")
    public int nextElement(@RequestParam("x") int x){
        return Lesson2c.nextElement(x);
    }

}

