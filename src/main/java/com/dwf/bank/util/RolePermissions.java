package com.dwf.bank.util;

public final class RolePermissions {
    // Roles individuales para referencia
    private static final String CLIENTE = "Cliente";
    private static final String DEPENDIENTE = "Dependiente";
    private static final String CAJERO = "Cajero";
    private static final String GERENTE_SUCURSAL = "Gerente_de_Sucursal";
    private static final String GERENTE_GENERAL = "Gerente_General";

    // Combinaciones comunes de roles
    public static final String SOLO_CAJEROS =
            "hasRole('Cajero')";

    public static final String SOLO_GERENTES =
            "hasRole('Gerente_de_Sucursal') or hasRole('Gerente_General')";

    public static final String PERSONAL_BANCARIO =
            "hasRole('Dependiente') or hasRole('Cajero') or " +
                    "hasRole('Gerente_de_Sucursal') or hasRole('Gerente_General')";

    public static final String TODOS_MENOS_CLIENTE =
            "hasRole('Dependiente') or hasRole('Cajero') or " +
                    "hasRole('Gerente_de_Sucursal') or hasRole('Gerente_General')";

    public static final String TODOS_MENOS_GERENTE_GENERAL =
            "hasRole('Dependiente') or hasRole('Cajero') or " +
                    "hasRole('Gerente_de_Sucursal') or hasRole('Cliente')";

    public static final String TODOS_LOS_ROLES =
            "hasRole('Cliente') or hasRole('Dependiente') or hasRole('Cajero') or " +
                    "hasRole('Gerente_de_Sucursal') or hasRole('Gerente_General')";

    public static final String GERENTES_Y_CAJEROS =
            "hasRole('Cajero') or hasRole('Gerente_de_Sucursal') or hasRole('Gerente_General')";

    public static final String DEPENDIENTE_Y_SUPERIORES =
            "hasRole('Dependiente') or hasRole('Cajero') or " +
                    "hasRole('Gerente_de_Sucursal') or hasRole('Gerente_General')";

    public static final String CLIENTE_Y_PERSONAL =
            "hasRole('Cliente') or hasRole('Dependiente') or hasRole('Cajero') or " +
                    "hasRole('Gerente_de_Sucursal') or hasRole('Gerente_General')";
}