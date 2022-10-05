package me.uquark.mcmagic.spell;

import me.uquark.mcmagic.Util;
import me.uquark.mcmagic.entity.SpellEntity;
import me.uquark.mcmagic.entity.renderer.SpellEntityRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;

public class HarmSpell extends Spell {
    public static final Identifier ID = new Identifier(Util.MOD_ID, "harm_spell");
    private static final float POWER = 7;
    public static EntityType<SpellEntity> ENTITY_TYPE;
    public static HarmSpell INSTANCE;

    public static void register() {
        ENTITY_TYPE = SpellEntity.register(
                ID,
                SpellEntity::new
        );
        INSTANCE = new HarmSpell();
        Spell.register(ID, INSTANCE);
    }

    public static void registerClient() {
        SpellEntityRenderer.register(ENTITY_TYPE, SpellEntityRenderer::new);
    }

    @Override
    public SpellEntity cast(LivingEntity caster) {
        SpellEntity entity = new SpellEntity(this, caster, ENTITY_TYPE);
        caster.world.spawnEntity(entity);
        return entity;
    }

    @Override
    public void onEntityHit(SpellEntity spellEntity, EntityHitResult entityHitResult) {
        Entity entity = entityHitResult.getEntity();
        if (entity == null)
            return;
        entity.damage(DamageSource.MAGIC, POWER);
    }

    @Override
    public void onBlockHit(SpellEntity spellEntity, BlockHitResult blockHitResult) {

    }

    @Override
    public void onHit(SpellEntity spellEntity, LivingEntity target) {

    }

    @Override
    public Identifier getId() {
        return ID;
    }
}