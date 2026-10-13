public class ComplexMatrix {
    private ComplexNumber[][] data;
    private int rows;
    private int cols;

    public ComplexMatrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.data = new ComplexNumber[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                this.data[i][j] = new ComplexNumber();
            }
        }
    }

    public int getRows() {
        return this.rows;
    }

    public int getCols() {
        return this.cols;
    }

    public ComplexNumber get(int i, int j) {
        return this.data[i][j];
    }

    public void set(int i, int j, ComplexNumber value) {
        this.data[i][j] = value;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < this.rows; i++) {
            sb.append("[");
            for (int j = 0; j < this.cols; j++) {
                sb.append(this.data[i][j]);
                if (j < this.cols - 1) {
                    sb.append(",  ");
                }
            }
            sb.append("]");
            if (i < this.rows - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    public ComplexMatrix add(ComplexMatrix other) {
        if (this.rows != other.rows || this.cols != other.cols) {
            throw new IllegalArgumentException("Размеры матриц не совпадают");
        }

        ComplexMatrix result = new ComplexMatrix(this.rows, this.cols);

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                ComplexNumber sum = this.data[i][j].add(other.data[i][j]);
                result.set(i, j, sum);
            }
        }
        return result;
    }

    public ComplexMatrix subtract(ComplexMatrix other) {
        if (this.rows != other.rows || this.cols != other.cols) {
            throw new IllegalArgumentException("Размеры матриц не совпадают");
        }

        ComplexMatrix result = new ComplexMatrix(this.rows, this.cols);

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                ComplexNumber sum = this.data[i][j].subtract(other.data[i][j]);
                result.set(i, j, sum);
            }
        }
        return result;
    }

    public ComplexMatrix multiply(ComplexMatrix other) {
        if(this.cols != other.rows) {
            throw new IllegalArgumentException("Размеры матриц не совпадают");
        }
        ComplexMatrix result = new ComplexMatrix(this.rows, other.cols);

        for(int i = 0; i < this.rows; i++) {
            for (int j = 0; j < other.cols; j++) {
                ComplexNumber sum = new ComplexNumber();
                for (int k = 0; k < this.cols; k++) {
                    ComplexNumber prod = this.data[i][k].multiply(other.data[k][j]);
                    sum = sum.add(prod);
                }
                result.set(i, j, sum);
            }
        }
        return result;
    }

    public ComplexMatrix transpose() {
        ComplexMatrix result = new ComplexMatrix(this.cols, this.rows);

        for(int i = 0; i < this.rows; i++) {
            for(int j = 0; j < this.cols; j++){
                result.set(j, i, this.data[i][j]);
            }
        }
        return result;
    }

    public ComplexNumber determinant(){
        if (this.rows != this.cols){
            throw new IllegalArgumentException("Определитель только для квадратных матриц");
        }

        if(this.rows == 1){
            return this.data[0][0];
        }

        if( this.rows == 2){
            ComplexNumber ad = this.data[0][0].multiply(this.data[1][1]);
            ComplexNumber bc = this.data[0][1].multiply(this.data[1][0]);
            return ad.subtract(bc);
        }

        if (this.rows == 3){
            ComplexNumber term1 = this.data[0][0].multiply(this.data[1][1]).multiply(this.data[2][2]);
            ComplexNumber term2 = this.data[0][1].multiply(this.data[1][2]).multiply(this.data[2][0]);
            ComplexNumber term3 = this.data[0][2].multiply(this.data[1][0]).multiply(this.data[2][1]);
            ComplexNumber term4 = this.data[0][2].multiply(this.data[1][1]).multiply(this.data[2][0]);
            ComplexNumber term5 = this.data[0][1].multiply(this.data[1][0]).multiply(this.data[2][2]);
            ComplexNumber term6 = this.data[0][0].multiply(this.data[1][2]).multiply(this.data[2][1]);
            return term1.add(term2).add(term3).subtract(term4).subtract(term5).subtract(term6);
        }

        throw new UnsupportedOperationException("Определитель только до 3х3");
    }

    public ComplexMatrix divide(ComplexMatrix other){
        if (this.rows != other.rows || this.cols != other.cols) {
            throw new IllegalArgumentException("Размеры матриц не совпадают");
        }
        ComplexMatrix result = new ComplexMatrix(this.rows, this.cols);

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                ComplexNumber div = this.data[i][j].divide(other.data[i][j]);
                result.set(i, j, div);
            }
        }
        return result;
    }

    @Override
    public boolean equals(Object o){
        if(this == o){
            return true;
        }
        if(o == null || getClass() != o.getClass()){
            return false;
        }
        ComplexMatrix other = (ComplexMatrix) o;

        if(this.rows != other.rows || this.cols != other.cols){
            return false;
        }

        for(int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                if (!this.data[i][j].equals(other.data[i][j])) {
                    return false;
                }
            }
        }
        return true;
    }
}
