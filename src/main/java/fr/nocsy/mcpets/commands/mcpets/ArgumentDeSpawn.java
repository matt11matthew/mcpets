package fr.nocsy.mcpets.commands.mcpets;

import fr.nocsy.mcpets.MCPets;
import fr.nocsy.mcpets.PPermission;
import fr.nocsy.mcpets.api.MCPetsAPI;
import fr.nocsy.mcpets.commands.AArgument;
import fr.nocsy.mcpets.data.Pet;
import fr.nocsy.mcpets.data.PetDespawnReason;
import fr.nocsy.mcpets.data.config.FormatArg;
import fr.nocsy.mcpets.data.config.Language;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ArgumentDeSpawn extends AArgument {

    public ArgumentDeSpawn(CommandSender sender, String[] args)
    {
        super("despawn", new int[]{1, 2}, sender, args);
    }

    @Override
    public boolean additionalConditions()
    {
        return sender.hasPermission(PPermission.ADMIN.getPermission());
    }

    @Override
    public void commandEffect() {
          if(args.length == 2 && sender.hasPermission(PPermission.ADMIN.getPermission())) {

              String playerName = args[1];
              Player target = Bukkit.getPlayer(playerName);
              if (target == null) {
                  Language.PLAYER_NOT_CONNECTED.sendMessageFormated(sender, new FormatArg("%player%", playerName));
                  return;
              }
              Pet activePet = MCPetsAPI.getActivePet(target.getUniqueId());
              if (activePet==null)return;
              activePet.despawn(PetDespawnReason.DEATH);
          }

    }
}
