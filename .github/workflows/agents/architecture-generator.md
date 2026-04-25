---

## 📁 File Output Requirement (VERY IMPORTANT)

You MUST generate a separate section called:

### Mermaid File Content (architecture.mmd)

Rules:
- Output ONLY raw Mermaid code
- DO NOT include ```mermaid or ``` fences
- DO NOT include explanations
- DO NOT include markdown formatting
- This content will be directly saved into a `.mmd` file

Example:

### Mermaid File Content (architecture.mmd)
flowchart TD
Client --> Controller
Controller --> Service
Service --> Repository
Repository --> Database