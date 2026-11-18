import { describe, it, expect, vi, beforeEach } from "vitest";
import { createRequire } from "module";

const require = createRequire(import.meta.url);
const db = require("../../../back/Config/db");
const luzesController = require("../../../back/Controllers/luzesController");

const criarRes = () => {
  const res: any = {};
  res.status = vi.fn().mockReturnThis();
  res.json = vi.fn().mockReturnThis();
  return res;
};

describe("UC4 – Energia / Luzes | Testes Unitários", () => {
  beforeEach(() => {
    vi.restoreAllMocks();
  });

  it("TU-01: deve registrar desligamento de luz (201) e salvar CPF e local no banco", async () => {
    // Arrange
    const spyDb = vi
      .spyOn(db, "executeQuery")
      .mockResolvedValueOnce([{ cpf: "16145354003" }]) // 1ª query: morador existe
      .mockResolvedValueOnce({ insertId: 10 });        // 2ª query: insert na tabela luzes

    const req = {
      body: { cpf_morador: "16145354003", descricao: "Garagem" },
    };
    const res = criarRes();

    // Act
    await luzesController.registrarLuz(req, res);

    // Assert
    expect(spyDb).toHaveBeenCalledTimes(2);
    expect(spyDb).toHaveBeenLastCalledWith(
      expect.stringContaining("INSERT INTO luzes"),
      ["16145354003", "Garagem"]
    );
    expect(res.status).toHaveBeenCalledWith(201);
    expect(res.json).toHaveBeenCalledWith({
      message: "Desligamento de luz registrado com sucesso!",
    });
  });

  it("TU-02: deve rejeitar o registro (400) sem consultar o banco quando o CPF estiver vazio", async () => {
    // Arrange
    const spyDb = vi.spyOn(db, "executeQuery");
    const req = {
      body: { cpf_morador: "", descricao: "Garagem" },
    };
    const res = criarRes();

    // Act
    await luzesController.registrarLuz(req, res);

    // Assert
    expect(spyDb).not.toHaveBeenCalled();
    expect(res.status).toHaveBeenCalledWith(400);
    expect(res.json).toHaveBeenCalledWith({
      error: "CPF do morador é obrigatório.",
    });
  });
});
