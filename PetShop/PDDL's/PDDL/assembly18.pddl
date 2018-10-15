
(DEFINE (PROBLEM ASSEM-16)
   (:DOMAIN ASSEMBLY)
   (:OBJECTS BRACKET DOODAD WIDGET-1 WIRE-2 WIRE KLUDGE WHATSIS
             THINGUMBOB UNIT GIMCRACK SOCKET COIL SPROCKET FOOBAR MOUNT
             FASTENER DEVICE CONTRAPTION HOOZAWHATSIE VALVE FROB TUBE
             CONNECTOR PLUG HACK WIDGET - ASSEMBLY
             SCAFFOLD HAMMOCK - RESOURCE)
   (:INIT (AVAILABLE WIDGET-1)
          (AVAILABLE WIRE-2)
          (AVAILABLE WIRE)
          (AVAILABLE KLUDGE)
          (AVAILABLE WHATSIS)
          (AVAILABLE THINGUMBOB)
          (AVAILABLE GIMCRACK)
          (AVAILABLE SOCKET)
          (AVAILABLE COIL)
          (AVAILABLE SPROCKET)
          (AVAILABLE FOOBAR)
          (AVAILABLE MOUNT)
          (AVAILABLE CONTRAPTION)
          (AVAILABLE HOOZAWHATSIE)
          (AVAILABLE FROB)
          (AVAILABLE TUBE)
          (AVAILABLE PLUG)
          (AVAILABLE HACK)
          (AVAILABLE WIDGET)
          (REQUIRES DOODAD SCAFFOLD)
          (REQUIRES UNIT HAMMOCK)
          (REQUIRES FASTENER HAMMOCK)
          (REQUIRES DEVICE HAMMOCK)
          (REQUIRES VALVE HAMMOCK)
          (REQUIRES CONNECTOR HAMMOCK)
          (PART-OF DOODAD BRACKET)
          (PART-OF UNIT BRACKET)
          (PART-OF FASTENER BRACKET)
          (PART-OF CONNECTOR BRACKET)
          (PART-OF WIDGET-1 DOODAD)
          (PART-OF WIRE-2 DOODAD)
          (PART-OF WIRE DOODAD)
          (PART-OF KLUDGE DOODAD)
          (PART-OF WHATSIS DOODAD)
          (PART-OF THINGUMBOB DOODAD)
          (PART-OF GIMCRACK UNIT)
          (PART-OF SOCKET UNIT)
          (PART-OF COIL UNIT)
          (PART-OF SPROCKET UNIT)
          (PART-OF FOOBAR UNIT)
          (PART-OF MOUNT UNIT)
          (PART-OF DEVICE FASTENER)
          (PART-OF VALVE FASTENER)
          (PART-OF CONTRAPTION DEVICE)
          (PART-OF HOOZAWHATSIE DEVICE)
          (PART-OF FROB VALVE)
          (PART-OF TUBE VALVE)
          (PART-OF PLUG CONNECTOR)
          (PART-OF HACK CONNECTOR)
          (PART-OF WIDGET CONNECTOR)
          (ASSEMBLE-ORDER UNIT CONNECTOR BRACKET)
          (ASSEMBLE-ORDER FASTENER DOODAD BRACKET)
          (ASSEMBLE-ORDER CONNECTOR DOODAD BRACKET)
          (ASSEMBLE-ORDER WIDGET-1 THINGUMBOB DOODAD)
          (ASSEMBLE-ORDER WIRE-2 WIRE DOODAD)
          (ASSEMBLE-ORDER WHATSIS WIRE DOODAD)
          (ASSEMBLE-ORDER DEVICE VALVE FASTENER)
          (ASSEMBLE-ORDER PLUG WIDGET CONNECTOR))
   (:GOAL (COMPLETE BRACKET)))
