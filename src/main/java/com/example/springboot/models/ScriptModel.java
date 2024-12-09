package com.example.springboot.models;


import com.example.springboot.enums.ScriptStatus;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "tb_scripts")


public class ScriptModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID script_id;

    private String script_name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ScriptStatus script_status;

    public UUID getScript_id() {
        return script_id;
    }

    public void setScript_id(UUID script_id) {
        this.script_id = script_id;
    }

    public String getScript_name() {
        return script_name;
    }

    public void setScript_name(String script_name) {
        this.script_name = script_name;
    }

    public ScriptStatus getScript_status() {
        return script_status;
    }

    public void setScript_status(ScriptStatus script_status) {
        this.script_status = script_status;
    }
}