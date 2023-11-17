import { useEffect, useState } from 'react';

import { useRecoilState } from 'recoil';

export function useSSRRecoilState<T>(recoilState: any, defaultValue: T) {
  const [isInitial, setIsInitial] = useState(true);
  const [value, setValue] = useRecoilState<T>(recoilState);
  const [isLoading, setIsLoading] = useState(true);

  useEffect(() => {
    setIsInitial(false);
    setIsLoading(false);
  }, []);

  return [isInitial ? defaultValue : value, setValue, isLoading] as const;
}
