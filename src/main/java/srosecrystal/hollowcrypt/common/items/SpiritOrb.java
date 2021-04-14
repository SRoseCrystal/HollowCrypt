package srosecrystal.hollowcrypt.common.items;

import moriyashiine.bewitchment.api.BewitchmentAPI;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.Optional;

public class SpiritOrb extends Item {


    public SpiritOrb() {
        super(new Settings().group(ItemGroup.TRANSPORTATION).maxCount(1));
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        if (!player.world.isClient()) {
            ServerPlayerEntity serverPlayer = (ServerPlayerEntity)player;
            BlockPos spawnpoint = new BlockPos(serverPlayer.getSpawnPointPosition());

                if (BewitchmentAPI.usePlayerMagic(player, 1, false)) {
                    serverPlayer.teleport(spawnpoint.getX(), spawnpoint.getY(), spawnpoint.getZ());
                    player.world.playSound(null, player.getBlockPos(), SoundEvents.ENTITY_ENDERMAN_TELEPORT, player.getSoundCategory(), 1, 1);
                    player.world.addParticle(ParticleTypes.ENCHANT, player.getParticleX(1), player.getY(), player.getParticleZ(1), 0, 0, 0);
                }
                else{
                    player.world.playSound(null, player.getBlockPos(), SoundEvents.ENTITY_GENERIC_EXTINGUISH_FIRE, player.getSoundCategory(), 1, 1);
                    player.world.addParticle(ParticleTypes.SMOKE, player.getParticleX(1), player.getY(), player.getParticleZ(1), 0, 0, 0);
                }

       }
        else{
            player.world.playSound(null, player.getBlockPos(), SoundEvents.ENTITY_GENERIC_EXTINGUISH_FIRE, player.getSoundCategory(), 1, 1);
            player.world.addParticle(ParticleTypes.SMOKE, player.getParticleX(1), player.getY(), player.getParticleZ(1), 0, 0, 0);
        }
        return new TypedActionResult<>(ActionResult.SUCCESS, player.getStackInHand(hand));
        }

    }

