declare module "@iopipe/iopipe" {
  export interface LibraryConfig {
    debug?: boolean;
    token?: string;
    networkTimeout?: number;
    timeoutWindow?: number;
}

  export type FunctionWrapper = (handler: any) => void;

  export function label(label: string): void;

  export function metric(label: string, value: number): void;

  export namespace mark {
      function start(label: string): void;
      function end(label: string): void;
  }

  export default function iopipe(config?: LibraryConfig): FunctionWrapper;

}
