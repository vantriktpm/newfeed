package com.newfeed.app.domain.usecase;

import com.newfeed.app.domain.repository.PostRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.Providers;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast",
    "deprecation",
    "nullness:initialization.field.uninitialized"
})
public final class ToggleLikeUseCase_Factory implements Factory<ToggleLikeUseCase> {
  private final Provider<PostRepository> postRepositoryProvider;

  public ToggleLikeUseCase_Factory(Provider<PostRepository> postRepositoryProvider) {
    this.postRepositoryProvider = postRepositoryProvider;
  }

  @Override
  public ToggleLikeUseCase get() {
    return newInstance(postRepositoryProvider.get());
  }

  public static ToggleLikeUseCase_Factory create(
      javax.inject.Provider<PostRepository> postRepositoryProvider) {
    return new ToggleLikeUseCase_Factory(Providers.asDaggerProvider(postRepositoryProvider));
  }

  public static ToggleLikeUseCase_Factory create(Provider<PostRepository> postRepositoryProvider) {
    return new ToggleLikeUseCase_Factory(postRepositoryProvider);
  }

  public static ToggleLikeUseCase newInstance(PostRepository postRepository) {
    return new ToggleLikeUseCase(postRepository);
  }
}
