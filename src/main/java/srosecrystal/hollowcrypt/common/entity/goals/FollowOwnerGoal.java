package srosecrystal.hollowcrypt.common.entity.goals;


import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.ai.pathing.LandPathNodeMaker;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldView;
import srosecrystal.hollowcrypt.common.entity.living.ThrallEntity;

public class FollowOwnerGoal extends Goal {

    private final ThrallEntity thrall;
    private LivingEntity owner;
    private final WorldView world;
    private final double field_6442;
    private final EntityNavigation navigation;
    private int field_6443;
    private final float maxDistance;
    private final float minDistance;
    private float field_6447;
    private final boolean field_21078;

    public FollowOwnerGoal(ThrallEntity thrall, LivingEntity owner, WorldView world, double speed, EntityNavigation navigation,  float maxDistance, float minDistance,  boolean field_21078) {
        this.thrall = thrall;
        this.owner = owner;
        this.world = world;
        this.field_6442 = speed;
        this.navigation = navigation;
        this.maxDistance = maxDistance;
        this.minDistance = minDistance;
        this.field_21078 = field_21078;
    }


    @Override
    public boolean canStart() {

        LivingEntity livingEntity = this.thrall.getOwner();

        if (livingEntity == null) {
            return false;
        }
        else if (livingEntity.isSpectator()) {
            return false;
        }
        else if (this.thrall.squaredDistanceTo(livingEntity) < (double)(this.minDistance * this.minDistance)) {
            return false;
        }
        else {
            this.owner = livingEntity;
            return true;
        }

    }


    @Override
    public boolean shouldContinue() {

        if (this.navigation.isIdle()) {
            return false;
        } else {
            return this.thrall.squaredDistanceTo(this.owner) > (double)(this.maxDistance * this.maxDistance);
        }


    }


    public void tick(){
        this.thrall.getLookControl().lookAt(this.owner, 10.0F, (float)this.thrall.getLookPitchSpeed());
        if (--this.field_6443 <= 0) {
            this.field_6443 = 10;
            if (!this.thrall.hasVehicle()) {
                if (this.thrall.squaredDistanceTo(this.owner) >= 144.0D) {
                    this.method_23345();
                } else {
                    this.navigation.startMovingTo(this.owner, this.field_6442);
                }

            }
        }



    }


    private void method_23345() {
        BlockPos blockPos = new BlockPos(this.owner.getBlockPos());

        for(int i = 0; i < 10; ++i) {
            int j = this.method_23342(-3, 3);
            int k = this.method_23342(-1, 1);
            int l = this.method_23342(-3, 3);
            boolean bl = this.method_23343(blockPos.getX() + j, blockPos.getY() + k, blockPos.getZ() + l);
            if (bl) {
                return;
            }
        }

    }


    private boolean method_23343(int i, int j, int k) {
        if (Math.abs((double)i - this.owner.getX()) < 2.0D && Math.abs((double)k - this.owner.getZ()) < 2.0D) {
            return false;
        } else if (!this.method_23344(new BlockPos(i, j, k))) {
            return false;
        } else {
            this.navigation.stop();
            return true;
        }
    }


    private boolean method_23344(BlockPos blockPos) {
        PathNodeType pathNodeType = LandPathNodeMaker.getLandNodeType(thrall.getEntityWorld(), new BlockPos.Mutable());
        if (pathNodeType != PathNodeType.WALKABLE) {
            return false;
        } else {
            BlockState blockState = this.world.getBlockState(blockPos.down());
            if (!this.field_21078 && blockState.getBlock() instanceof LeavesBlock) {
                return false;
            } else {
                BlockPos blockPos2 = blockPos.subtract(new BlockPos(this.thrall.getBlockPos()));
                return this.world.isSpaceEmpty(this.thrall, this.thrall.getBoundingBox().offset(blockPos2));
            }
        }
    }


    private int method_23342(int i, int j) {
        return this.thrall.getRandom().nextInt(j - i + 1) + i;
    }





}
