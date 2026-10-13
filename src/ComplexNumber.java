public class ComplexNumber {
    private double re;
    private double im;

    public ComplexNumber(){
        this.re = 0;
        this.im = 0;
    }
    public ComplexNumber(double re){
        this.re = re;
        this.im = 0;
    }
    public ComplexNumber(double re, double im){
        this.re = re;
        this.im = im;
    }
    public double getRe(){
        return re;
    }
    public double getIm(){
        return im;
    }
    public void setRe(double re) {
        this.re = re;
    }
    public void setIm(double im) {
        this.im = im;
    }

    public ComplexNumber add(ComplexNumber other){
        double newRe = this.re + other.re;
        double newIm = this.im + other.im;
        return new ComplexNumber(newRe, newIm);
    }

    public ComplexNumber subtract(ComplexNumber other){
        double newRe = this.re - other.re;
        double newIm = this.im - other.im;
        return new ComplexNumber(newRe, newIm);
    }

    public ComplexNumber multiply(ComplexNumber other){
        double newRe = this.re * other.re - this.im * other.im;
        double newIm = this.re * other.im + this.im * other.re;
        return new ComplexNumber(newRe, newIm);
    }

    public ComplexNumber divide(ComplexNumber other) {
        if (other.re == 0 && other.im == 0) {
            throw new ArithmeticException("Деление на ноль");
        }
        double denom = other.re * other.re + other.im * other.im;
        double newRe = (this.re * other.re + this.im * other.im) / denom;
        double newIm = (this.im * other.re - this.re * other.im) / denom;
        return new ComplexNumber(newRe, newIm);
    }

    public ComplexNumber conjugate(){
        double newRe = this.re;
        double newIm = -this.im;
        return new ComplexNumber(newRe, newIm);
    }

    public double abs(){
        double root = Math.sqrt(this.re * this.re + this.im * this.im);
        return root;
    }

    @Override
    public String toString(){
        if (this.im == 0){
            return this.re + "";
        }
        if (this.re == 0 && this.im != 0){
            return this.im + "i";
        }
        if (this.im > 0){
            return this.re + " + " + this.im + "i";
        }
        return this.re + " - " + -this.im + "i";
    }

    @Override
    public boolean equals(Object o){
        if(this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        ComplexNumber other = (ComplexNumber) o;
        return Math.abs(this.re - other.re) < 1e-9
                && Math.abs(this.im - other.im) < 1e-9;
    }
}
