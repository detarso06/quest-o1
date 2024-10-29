//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

        interface Calculavel {
            double calcularImposto();
            double calcularJuros();
            double calcularLucro();
            double calcularDesconto();
            double calcularTaxaManuseio();
            double calcularPrecoFinal();
            double calcularTotal();
        }

        enum CategoriaProduto {
            ELETRONICO, ALIMENTO, PAPELARIA;
        }


        class Cliente {
            private String nome;
            private String cpf;

            public Cliente(String nome, String cpf) {
                this.nome = nome;
                this.cpf = cpf;
            }

            public String getNome() {
                return nome;
            }

            public String getCpf() {
                return cpf;
            }
        }


        class Produto implements Calculavel {
            private String nome;
            private double preco;
            private CategoriaProduto categoria;

            public Produto(String nome, double preco, CategoriaProduto categoria) {
                this.nome = nome;
                this.preco = preco;
                this.categoria = categoria;
            }

            @Override
            public double calcularImposto() {
                switch (categoria) {
                    case ELETRONICO: return preco * 0.15;
                    case ALIMENTO: return preco * 0.05;
                    case PAPELARIA: return preco * 0.10;
                    default: return 0;
                }
            }

            @Override
            public double calcularJuros() {
                return 0;
            }

            @Override
            public double calcularLucro() {
                return preco * 0.20;
            }

            @Override
            public double calcularDesconto() {
                return preco * 0.10;
            }

            @Override
            public double calcularTaxaManuseio() {
                return 5.0;
            }

            @Override
            public double calcularPrecoFinal() {
                return preco + calcularImposto() - calcularDesconto() + calcularTaxaManuseio();
            }

            @Override
            public double calcularTotal() {
                return calcularPrecoFinal() + calcularJuros();
            }

            public String getNome() {
                return nome;
            }

            public double getPreco() {
                return preco;
            }
        }


        class NotaFiscal {
            private Cliente cliente;
            private Produto produto1, produto2, produto3;

            public void adicionaCliente(Cliente cliente) {
                this.cliente = cliente;
            }

            public void adicionaProduto1(Produto produto) {
                this.produto1 = produto;
            }

            public void adicionaProduto2(Produto produto) {
                this.produto2 = produto;
            }

            public void adicionaProduto3(Produto produto) {
                this.produto3 = produto;
            }

            public void exibirNotaFiscal() {
                System.out.println("Cliente: " + cliente.getNome() + " (CPF: " + cliente.getCpf() + ")");
                System.out.println("Produtos:");

                Produto[] produtos = {produto1, produto2, produto3};
                double totalNota = 0;

                for (Produto produto : produtos) {
                    if (produto != null) {
                        System.out.println("- " + produto.getNome() + ": R$ " + produto.getPreco());
                        totalNota += produto.calcularTotal();
                    }
                }

                System.out.println("Total da Nota Fiscal: R$ " + totalNota);
            }
        }


        public class Main {
            public static void main(String[] args) {
                Cliente cliente = new Cliente("Paulo de Tarso", "707.708.054-42");
                Produto produto1 = new Produto("maquina de supino", 3000.00, CategoriaProduto.ELETRONICO);
                Produto produto2 = new Produto("barra de proteina", 5.00, CategoriaProduto.ALIMENTO);
                Produto produto3 = new Produto("whey", 20.00, CategoriaProduto.PAPELARIA);

                NotaFiscal notaFiscal = new NotaFiscal();
                notaFiscal.adicionaCliente(cliente);
                notaFiscal.adicionaProduto1(produto1);
                notaFiscal.adicionaProduto2(produto2);
                notaFiscal.adicionaProduto3(produto3);

                notaFiscal.exibirNotaFiscal();
            }
        }