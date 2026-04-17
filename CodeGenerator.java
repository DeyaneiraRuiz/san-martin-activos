import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CodeGenerator {

    private static final String BASE_PATH = "src/main/java/com/sgsi/";

    public static void main(String[] args) {
        // Module, Entity Name, Optional Type (ID type), field to update, more fields
        generate("organizacion", "Area", "String nombre, String descripcion");
        generate("organizacion", "Grupo", "String nombre, String descripcion");
        generate("organizacion", "Propietario", "String nombre, String email, Integer areaId, Boolean activo");

        // Catalogos
        generate("catalogos", "CatTipoActivo", "String nombre");
        generate("catalogos", "CatEstadoActivo", "String nombre");
        generate("catalogos", "CatNivelAutomatizacion", "String nombre");
        generate("catalogos", "CatPeriodicidad", "String nombre");
        generate("catalogos", "CatTipoNivel", "String nombre");
        generate("catalogos", "CatTipoTicket", "String nombre");
        generate("catalogos", "CatTipoVinculo", "String nombre");
        generate("catalogos", "CatEstadoIncidente", "String nombre");
        
        generate("catalogos", "CatNivel", "String nombre, Integer valor, Integer tipoNivelId");
        
        System.out.println("Generation completed with BeanUtils!");
    }

    private static void generate(String modulo, String entity, String fields) {
        try {
            createDto(modulo, entity, fields);
            createService(modulo, entity);
            createController(modulo, entity);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void createDto(String modulo, String entity, String fields) throws IOException {
        String dir = BASE_PATH + modulo + "/dto/";
        new File(dir).mkdirs();
        File file = new File(dir + entity + "DTO.java");
        FileWriter writer = new FileWriter(file);
        
        StringBuilder sb = new StringBuilder();
        sb.append("package com.sgsi.").append(modulo).append(".dto;\n\n");
        sb.append("import lombok.Data;\n\n");
        sb.append("@Data\n");
        sb.append("public class ").append(entity).append("DTO {\n");
        sb.append("    private Integer id;\n");
        
        String[] splitFields = fields.split(",");
        for (String field : splitFields) {
            String f = field.trim();
            if (!f.isEmpty()) {
                sb.append("    private ").append(f).append(";\n");
            }
        }
        sb.append("}\n");
        
        writer.write(sb.toString());
        writer.close();
    }

    private static void createService(String modulo, String entity) throws IOException {
        String dir = BASE_PATH + modulo + "/service/";
        new File(dir).mkdirs();
        File file = new File(dir + entity + "Service.java");
        FileWriter writer = new FileWriter(file);
        
        String lowerEntity = entity.substring(0,1).toLowerCase() + entity.substring(1);
        
        StringBuilder sb = new StringBuilder();
        sb.append("package com.sgsi.").append(modulo).append(".service;\n\n");
        sb.append("import com.sgsi.").append(modulo).append(".entity.").append(entity).append(";\n");
        sb.append("import com.sgsi.").append(modulo).append(".repository.").append(entity).append("Repository;\n");
        sb.append("import com.sgsi.").append(modulo).append(".dto.").append(entity).append("DTO;\n");
        sb.append("import org.springframework.beans.BeanUtils;\n");
        sb.append("import org.springframework.beans.factory.annotation.Autowired;\n");
        sb.append("import org.springframework.stereotype.Service;\n");
        sb.append("import org.springframework.transaction.annotation.Transactional;\n");
        sb.append("import java.util.List;\n");
        sb.append("import java.util.stream.Collectors;\n\n");
        sb.append("@Service\n");
        sb.append("public class ").append(entity).append("Service {\n\n");
        sb.append("    @Autowired\n");
        sb.append("    private ").append(entity).append("Repository ").append(lowerEntity).append("Repository;\n\n");
        
        // Find All
        sb.append("    public List<").append(entity).append("DTO> findAll() {\n");
        sb.append("        return ").append(lowerEntity).append("Repository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());\n");
        sb.append("    }\n\n");
        
        // Find By ID
        sb.append("    public ").append(entity).append("DTO findById(Integer id) {\n");
        sb.append("        return ").append(lowerEntity).append("Repository.findById(id).map(this::mapToDTO)\n");
        sb.append("                 .orElseThrow(() -> new RuntimeException(\"").append(entity).append(" no encontrado\"));\n");
        sb.append("    }\n\n");

        // Create
        sb.append("    @Transactional\n");
        sb.append("    public ").append(entity).append("DTO create(").append(entity).append("DTO dto) {\n");
        sb.append("        ").append(entity).append(" entity = new ").append(entity).append("();\n");
        sb.append("        mapToEntity(dto, entity);\n");
        sb.append("        return mapToDTO(").append(lowerEntity).append("Repository.save(entity));\n");
        sb.append("    }\n\n");

        // Update
        sb.append("    @Transactional\n");
        sb.append("    public ").append(entity).append("DTO update(Integer id, ").append(entity).append("DTO dto) {\n");
        sb.append("        ").append(entity).append(" entity = ").append(lowerEntity).append("Repository.findById(id)\n");
        sb.append("                 .orElseThrow(() -> new RuntimeException(\"").append(entity).append(" no encontrado\"));\n");
        sb.append("        mapToEntity(dto, entity);\n");
        sb.append("        return mapToDTO(").append(lowerEntity).append("Repository.save(entity));\n");
        sb.append("    }\n\n");

        // Delete
        sb.append("    @Transactional\n");
        sb.append("    public void delete(Integer id) {\n");
        sb.append("        ").append(lowerEntity).append("Repository.deleteById(id);\n");
        sb.append("    }\n\n");
        
        sb.append("    private ").append(entity).append("DTO mapToDTO(").append(entity).append(" entity) {\n");
        sb.append("        ").append(entity).append("DTO dto = new ").append(entity).append("DTO();\n");
        sb.append("        BeanUtils.copyProperties(entity, dto);\n");
        sb.append("        return dto;\n");
        sb.append("    }\n\n");
        
        sb.append("    private void mapToEntity(").append(entity).append("DTO dto, ").append(entity).append(" entity) {\n");
        sb.append("        BeanUtils.copyProperties(dto, entity, \"id\");\n");
        sb.append("    }\n");
        
        sb.append("}\n");
        
        writer.write(sb.toString());
        writer.close();
    }

    private static void createController(String modulo, String entity) throws IOException {
        String dir = BASE_PATH + modulo + "/controller/";
        new File(dir).mkdirs();
        File file = new File(dir + entity + "Controller.java");
        FileWriter writer = new FileWriter(file);
        
        String lowerEntity = entity.substring(0,1).toLowerCase() + entity.substring(1);
        String urlPattern = lowerEntity.replaceAll("([a-z])([A-Z]+)", "$1-$2").toLowerCase() + "s";
        if (entity.equals("Propietario")) urlPattern = "propietarios";
        if (entity.equals("Grupo")) urlPattern = "grupos";
        if (entity.equals("Area")) urlPattern = "areas";
        
        StringBuilder sb = new StringBuilder();
        sb.append("package com.sgsi.").append(modulo).append(".controller;\n\n");
        sb.append("import com.sgsi.").append(modulo).append(".dto.").append(entity).append("DTO;\n");
        sb.append("import com.sgsi.").append(modulo).append(".service.").append(entity).append("Service;\n");
        sb.append("import org.springframework.beans.factory.annotation.Autowired;\n");
        sb.append("import org.springframework.http.ResponseEntity;\n");
        sb.append("import org.springframework.web.bind.annotation.*;\n\n");
        sb.append("import java.util.List;\n\n");
        
        sb.append("@RestController\n");
        sb.append("@RequestMapping(\"/api/").append(urlPattern).append("\")\n");
        sb.append("public class ").append(entity).append("Controller {\n\n");
        
        sb.append("    @Autowired\n");
        sb.append("    private ").append(entity).append("Service ").append(lowerEntity).append("Service;\n\n");
        
        // GET all
        sb.append("    @GetMapping\n");
        sb.append("    public ResponseEntity<List<").append(entity).append("DTO>> getAll() {\n");
        sb.append("        return ResponseEntity.ok(").append(lowerEntity).append("Service.findAll());\n");
        sb.append("    }\n\n");
        
        // GET by ID
        sb.append("    @GetMapping(\"/{id}\")\n");
        sb.append("    public ResponseEntity<").append(entity).append("DTO> getById(@PathVariable Integer id) {\n");
        sb.append("        return ResponseEntity.ok(").append(lowerEntity).append("Service.findById(id));\n");
        sb.append("    }\n\n");
        
        // POST
        sb.append("    @PostMapping\n");
        sb.append("    public ResponseEntity<").append(entity).append("DTO> create(@RequestBody ").append(entity).append("DTO dto) {\n");
        sb.append("        return ResponseEntity.ok(").append(lowerEntity).append("Service.create(dto));\n");
        sb.append("    }\n\n");
        
        // PUT
        sb.append("    @PutMapping(\"/{id}\")\n");
        sb.append("    public ResponseEntity<").append(entity).append("DTO> update(@PathVariable Integer id, @RequestBody ").append(entity).append("DTO dto) {\n");
        sb.append("        return ResponseEntity.ok(").append(lowerEntity).append("Service.update(id, dto));\n");
        sb.append("    }\n\n");
        
        // DELETE
        sb.append("    @DeleteMapping(\"/{id}\")\n");
        sb.append("    public ResponseEntity<Void> delete(@PathVariable Integer id) {\n");
        sb.append("        ").append(lowerEntity).append("Service.delete(id);\n");
        sb.append("        return ResponseEntity.noContent().build();\n");
        sb.append("    }\n");
        
        sb.append("}\n");
        
        writer.write(sb.toString());
        writer.close();
    }
}
